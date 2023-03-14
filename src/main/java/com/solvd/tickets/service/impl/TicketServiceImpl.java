package com.solvd.tickets.service.impl;

import com.solvd.tickets.domain.Ticket;
import com.solvd.tickets.domain.exception.NoFreePlacesException;
import com.solvd.tickets.repository.TicketRepository;
import com.solvd.tickets.service.TicketService;
import com.solvd.tickets.service.property.WebProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final WebProperties webProperties;

    @Override
    public Mono<Ticket> create(Ticket ticket) {
        WebClient webClient = WebClient.create("http://" + webProperties.getHost());

        Mono<Integer> placesMono = webClient
                .get()
                .uri("/films/api/v1/films/places/" + ticket.getFilmId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Integer.class);
        return placesMono.flatMap(value -> {
            if(value < ticket.getAmount()) {
                return Mono.error(new NoFreePlacesException("There are only " + value + " free places"));
            }
            else {
                Mono<String> response = webClient.put()
                        .uri("/films/api/v1/films/" + ticket.getFilmId() + "/" + ticket.getAmount())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .bodyToMono(String.class);
                return ticketRepository.save(ticket);
            }
        });
        //TODO update amount of film showing
    }

    @Override
    public Flux<Ticket> findAllByUserId(Long userId) {
        return ticketRepository.findAllByUserId(userId);
    }

}

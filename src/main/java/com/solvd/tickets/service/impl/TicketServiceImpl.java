package com.solvd.tickets.service.impl;

import com.solvd.tickets.domain.Ticket;
import com.solvd.tickets.repository.TicketRepository;
import com.solvd.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Mono<Ticket> create(Ticket ticket) {
        //TODO check for free places
        return ticketRepository.save(ticket);
    }

    @Override
    public Flux<Ticket> findAllByUserId(Long userId) {
        return ticketRepository.findAllByUserId(userId);
    }

}

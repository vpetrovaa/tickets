package com.solvd.tickets.service;

import com.solvd.tickets.domain.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {

    Mono<Ticket> create(Ticket ticket);

    Flux<Ticket> findAllByUserId(Long userId);

}

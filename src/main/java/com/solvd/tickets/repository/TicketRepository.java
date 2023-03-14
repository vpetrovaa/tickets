package com.solvd.tickets.repository;

import com.solvd.tickets.domain.Ticket;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TicketRepository extends R2dbcRepository<Ticket, Long> {

    Flux<Ticket> findAllByUserId(Long userId);

}

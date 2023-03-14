package com.solvd.tickets.web.controller;

import com.solvd.tickets.domain.Ticket;
import com.solvd.tickets.service.TicketService;
import com.solvd.tickets.web.dto.TicketDto;
import com.solvd.tickets.web.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping("/{id}")
    public Flux<TicketDto> findById(@PathVariable Long id) {
        Flux<Ticket> tickets = ticketService.findAllByUserId(id);
        return tickets.map(ticketMapper::entityToDto);
    }

    @PostMapping
    public Mono<TicketDto> create(@RequestBody @Validated TicketDto ticketDto) {
        Ticket ticketMapped = ticketMapper.dtoToEntity(ticketDto);
        Mono<Ticket> ticket = ticketService.create(ticketMapped);
        return ticket.map(ticketMapper::entityToDto);
    }

}

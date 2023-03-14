package com.solvd.tickets.web.mapper;

import com.solvd.tickets.domain.Ticket;
import com.solvd.tickets.web.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto entityToDto(Ticket ticket);

    Ticket dtoToEntity(TicketDto ticketDto);

}

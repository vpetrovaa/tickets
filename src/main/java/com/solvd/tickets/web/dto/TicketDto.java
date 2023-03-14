package com.solvd.tickets.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketDto {

    private Long id;

    @NotNull(message = "Amount cant be null")
    @Min(1)
    private Integer amount;

    @NotNull(message = "User id cant be null")
    private Long userId;

    @NotNull(message = "Film id cant be null")
    private Long filmId;

}

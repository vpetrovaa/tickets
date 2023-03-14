package com.solvd.tickets.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "tickets")
public class Ticket {

    @Id
    private Long id;

    private Integer amount;

    private Long userId;

    private Long filmId;

}

package com.solvd.tickets.domain.exception;

public class NoFreePlacesException extends RuntimeException{

    public NoFreePlacesException(String message) {
        super(message);
    }

}

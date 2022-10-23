package com.drone.dispatcher.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends StatusException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null);
    }
}

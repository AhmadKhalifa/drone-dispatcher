package com.drone.dispatcher.domain.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends StatusException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, null);
    }
}

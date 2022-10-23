package com.drone.dispatcher.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends StatusException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, null);
    }
}

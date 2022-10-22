package com.drone.dispatcher.base.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends StatusException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, HttpStatus.NOT_FOUND, cause);
    }
}

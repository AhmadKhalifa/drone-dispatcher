package com.drone.dispatcher.domain.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends StatusException {

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST, null);
    }
}

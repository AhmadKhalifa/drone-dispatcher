package com.drone.dispatcher.base.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends StatusException {

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST, null);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, HttpStatus.BAD_REQUEST, cause);
    }
}

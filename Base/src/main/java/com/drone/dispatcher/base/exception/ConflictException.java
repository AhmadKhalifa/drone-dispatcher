package com.drone.dispatcher.base.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends StatusException {

    public ConflictException(String message, Throwable cause) {
        super(message, HttpStatus.CONFLICT, cause);
    }
}

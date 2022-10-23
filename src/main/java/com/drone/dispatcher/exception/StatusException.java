package com.drone.dispatcher.exception;

import org.springframework.http.HttpStatus;

public class StatusException extends Exception {

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public StatusException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}

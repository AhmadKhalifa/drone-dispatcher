package com.drone.dispatcher.exception;

import org.springframework.http.HttpStatus;

public class IntervalServerErrorException extends StatusException {

    public IntervalServerErrorException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}

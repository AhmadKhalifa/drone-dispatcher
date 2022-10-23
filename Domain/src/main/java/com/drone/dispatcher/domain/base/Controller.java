package com.drone.dispatcher.domain.base;

import com.drone.dispatcher.domain.exception.StatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class Controller {
    protected  <T> Mono<ResponseEntity<?>> response(Mono<T> mono) {
        return mono
                .map(Response::success)
                .onErrorResume(throwable -> {
                    StatusException statusException = throwable instanceof StatusException ?
                            (StatusException) throwable :
                            new StatusException(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, throwable);
                    return Mono.just(Response.failure(statusException));
                })
                .map(Response::toResponseEntity);
    }
}

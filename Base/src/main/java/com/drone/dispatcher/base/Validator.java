package com.drone.dispatcher.base;

import com.drone.dispatcher.base.exception.IntervalServerErrorException;
import com.drone.dispatcher.base.exception.StatusException;
import reactor.core.publisher.Mono;

public abstract class Validator<T, S extends StatusException> {

    protected abstract Mono<Boolean> isValid(T parameter);

    protected abstract S getException(T parameter);

    public final Mono<T> validate(T parameter) {
        return isValid(parameter)
                .defaultIfEmpty(false)
                .onErrorMap(throwable -> new IntervalServerErrorException(
                        String.format("Error validating %s: %s", parameter, throwable.getMessage()),
                        throwable
                ))
                .flatMap(isValid -> isValid ? Mono.justOrEmpty(parameter) : Mono.error(getException(parameter)));
    }
}

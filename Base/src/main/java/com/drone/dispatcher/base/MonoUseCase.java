package com.drone.dispatcher.base;

import reactor.core.publisher.Mono;

public interface MonoUseCase<T, R> {

    Mono<R> apply(T parameters);
}

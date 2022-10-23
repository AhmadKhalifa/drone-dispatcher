package com.drone.dispatcher.base;

import reactor.core.publisher.Flux;

public interface FluxUseCase<T, R> {

    Flux<R> apply(T parameters);
}

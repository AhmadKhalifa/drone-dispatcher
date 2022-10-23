package com.drone.dispatcher.domain.base;

import reactor.core.publisher.Flux;

public interface FluxUseCase<T, R> {

    Flux<R> apply(T parameters);
}

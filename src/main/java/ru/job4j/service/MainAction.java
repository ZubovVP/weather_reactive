package ru.job4j.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 11.10.2021.
 */
public interface MainAction<E> {
    Mono<E> add(E element);

    Flux<E> getAll();

    Mono<E> findById(Integer id);
}

package ru.job4j.parser;

import reactor.core.publisher.Mono;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 11.10.2021.
 */
public interface ParseAble<E, I> {
    Mono<E> parse(I element);
}

package ru.job4j.service;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 11.10.2021.
 */
public interface MainAction<E> {
    E add(E element);

    Iterable<E> getAll();

    E findById(Integer id);
}

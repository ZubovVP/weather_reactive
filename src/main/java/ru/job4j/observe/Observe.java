package ru.job4j.observe;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 08.10.2021.
 */
public interface Observe<T> {
    void receive(T model);
}

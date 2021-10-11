package ru.job4j.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.job4j.model.City;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 09.10.2021.
 */
public interface CityRepository extends ReactiveCrudRepository<City, Integer> {
}

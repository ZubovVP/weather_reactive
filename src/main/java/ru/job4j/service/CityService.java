package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.City;
import ru.job4j.repository.CityRepository;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 09.10.2021.
 */
@Service
public class CityService implements MainAction<City> {
    @Autowired
    private CityRepository repository;

    @Override
    public City add(City element) {
        return this.repository.save(element);
    }

    @Override
    public Iterable<City> getAll() {
        return repository.findAll();
    }

    @Override
    public City findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}

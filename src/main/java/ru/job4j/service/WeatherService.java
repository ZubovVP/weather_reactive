package ru.job4j.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.model.City;
import ru.job4j.model.Weather;
import ru.job4j.parser.WeatherParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.03.2021.
 */
@Service
public class WeatherService {
    private final WeatherParser weatherParser = new WeatherParser();
    private final CityService cityService;

    public WeatherService(CityService cityService) {
        this.cityService = cityService;
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(this.weatherParser.parse(List.of(this.cityService.findById(id))).get(0));
    }

    public Flux<Weather> all() {
        List<City> cities = new ArrayList<>();
        this.cityService.getAll().forEach(cities::add);
        return Flux.fromIterable(this.weatherParser.parse(cities));
    }

    public Mono<Weather> getHottest() {
        List<City> cities = new ArrayList<>();
        this.cityService.getAll().forEach(cities::add);
        return Mono.justOrEmpty(this.weatherParser.parse(cities).stream().max(Comparator.comparing(Weather::getTemperature)));
    }

    public Flux<Weather> getCityGreatThen(Integer amount) {
        List<City> cities = new ArrayList<>();
        this.cityService.getAll().forEach(cities::add);
        return Flux.fromIterable(this.weatherParser.parse(cities).stream().filter(e -> e.getTemperature() > amount).collect(Collectors.toList()));
    }
}

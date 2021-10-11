package ru.job4j.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.model.Weather;
import ru.job4j.parser.WeatherParser;
import java.util.Comparator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.03.2021.
 */
@Service
public class WeatherService {
    private final WeatherParser weatherParser;
    private final CityService cityService;

    public WeatherService(WeatherParser weatherParser, CityService cityService) {
        this.weatherParser = weatherParser;
        this.cityService = cityService;
    }

    public Mono<Weather> findById(Integer id) {
        return this.cityService.findById(id).flatMap(this.weatherParser::parse);
    }

    public Flux<Weather> all() {
        return this.cityService.getAll().flatMap(this.weatherParser::parse);
    }

    public Mono<Weather> getHottest() {
        return Mono.justOrEmpty(this.cityService.getAll().flatMap(this.weatherParser::parse).toStream().max(Comparator.comparing(Weather::getTemperature)));

    }

    public Flux<Weather> getCityGreatThen(Integer amount) {
        return this.cityService.getAll().flatMap(this.weatherParser::parse).filter(e -> e.getTemperature() > amount);
    }
}

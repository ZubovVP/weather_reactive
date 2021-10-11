package ru.job4j.contoller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.model.Weather;
import ru.job4j.service.WeatherService;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.03.2021.
 */
@RestController
@RequestMapping("/weather")
public class WeatherControl {
    private final WeatherService weathers;

    public WeatherControl(WeatherService weathers) {
        this.weathers = weathers;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        //Add duration
//        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
//        return Flux.zip(data, delay).map(Tuple2::getT1);
        return weathers.all();
    }

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return this.weathers.findById(id);
    }

    @GetMapping(value = "/hottest")
    public Mono<Weather> getHottest() {
        return this.weathers.getHottest();
    }

    @GetMapping(value = "/cityGreatThen/{amount}")
    public Flux<Weather> getCityGreatThen(@PathVariable Integer amount) {
        return this.weathers.getCityGreatThen(amount);
    }
}

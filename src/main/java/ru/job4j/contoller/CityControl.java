package ru.job4j.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.model.City;
import ru.job4j.service.CityService;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 10.10.2021.
 */
@RestController
@RequestMapping("/city")
public class CityControl {
    private final CityService cityService;

    public CityControl(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/add")
    public City addCity(@RequestBody City city) {
        return this.cityService.add(city);
    }
}

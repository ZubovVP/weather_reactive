package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.City;
import ru.job4j.model.Weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 10.10.2021.
 */
public class WeatherParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherParser.class);

    public List<Weather> parse(List<City> cities) {
        List<Weather> result = new ArrayList<>();
        String mainLink = loadLink();
        for (City city : cities) {
            String link = String.format("%s%s", mainLink, city.getName());
            try {
                Document doc = Jsoup.connect(link).get();
                Elements row = doc.select("body > div.b-page__container > div.content.content_compressed.i-bem > div.content__top > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value.temp__value_with-unit");
                for (Element temp : row) {
                    Weather weather = new Weather();
                    weather.setCity(city);
                    weather.setId(city.getId());
                    String value = temp.text().replace((char) 8722, '-');
                    weather.setTemperature(Integer.parseInt(value));
                    result.add(weather);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    private String loadLink() {
        Properties props = new Properties();
        String mainLink = null;
        try (InputStream in = WeatherParser.class.getClassLoader().getResourceAsStream("parser.properties")) {
            props.load(in);
            mainLink = props.getProperty("link.parse.weather");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return mainLink;
    }
}

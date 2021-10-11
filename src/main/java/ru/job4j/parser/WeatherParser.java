package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.job4j.model.City;
import ru.job4j.model.Weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 10.10.2021.
 */
@Component
public class WeatherParser implements ParseAble<Weather, City> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherParser.class);
    private static final String mainLink;

    static {
        mainLink = loadLink();
    }

    @Override
    public Mono<Weather> parse(City element) {
        Weather result = null;
        String link = String.format("%s%s", mainLink, element.getName());
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select("body > div.b-page__container > div.content.content_compressed.i-bem > div.content__top > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value.temp__value_with-unit");
            for (Element temp : row) {
                Weather weather = new Weather();
                weather.setCity(element);
                weather.setId(element.getId());
                String value = temp.text().replace((char) 8722, '-');
                weather.setTemperature(Integer.parseInt(value));
                result = weather;
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Mono.justOrEmpty(result);
    }

    private static String loadLink() {
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

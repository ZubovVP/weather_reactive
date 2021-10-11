package ru.job4j.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.03.2021.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private int id;
    private City city;
    private int temperature;
}

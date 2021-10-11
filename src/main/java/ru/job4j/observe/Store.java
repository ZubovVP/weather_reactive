package ru.job4j.observe;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 08.10.2021.
 */

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<String> data = List.of("first", "Second", "Third");

    public void getByReact(Observe<String> observe) throws InterruptedException {
        for (String datum : data) {
            Thread.sleep(1000);
            observe.receive(datum);
        }
    }

    public List<String> get() throws InterruptedException {
        List<String> rsl = new ArrayList<>();
        for (String datum : data) {
            Thread.sleep(1000);
            rsl.add(datum);
        }
        return rsl;
    }

//        public static void main(String[] args) throws InterruptedException {
//            var store = new Store();
//            List<String> data = store.get();
//            for (String datum : data) {
//                System.out.println(datum);
//            }
//        }

    public static void main(String[] args) throws InterruptedException {
        var store = new Store();
        store.getByReact(System.out::println);
    }
    }

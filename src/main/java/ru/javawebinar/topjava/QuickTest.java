package ru.javawebinar.topjava;

import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

/**
 * Created by kh0ma on 20.09.16.
 */
public class QuickTest {
    public static void main(String[] args) {

        //new InMemoryMealRepositoryImpl().getAll().forEach(System.out::println);
        System.out.println(new InMemoryMealRepositoryImpl().getAll(2));

    }
}

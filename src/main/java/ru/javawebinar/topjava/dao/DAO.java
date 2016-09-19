package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by kh0ma on 16.09.16.
 */
public interface DAO {
    List<Meal> read();
    void create(Meal meal);
    void deleteById(int id);
    void update(int id, Meal meal);
    Meal getByid(int id);
}

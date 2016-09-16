package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by kh0ma on 16.09.16.
 */
public interface DAO {
    List<MealWithExceed> read();
    void create(MealWithExceed mealWithExceed);
    void deleteById(int id);
    void update(int id, MealWithExceed mealWithExceed);
    MealWithExceed getByid(int id);
}

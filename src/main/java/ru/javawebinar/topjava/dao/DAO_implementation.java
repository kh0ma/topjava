package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

/**
 * Created by kh0ma on 16.09.16.
 */
public class DAO_implementation implements DAO{
    private List<Meal> mealList;

    public DAO_implementation() {
        this.mealList = MealsUtil.generateMeal();
    }


    @Override
    public List<Meal> read() {
        return this.mealList;
    }

    @Override
    public void create(Meal meal) {
        this.mealList.add(meal);
    }

    @Override
    public void deleteById(int id) {
        this.mealList.remove(id);
    }

    @Override
    public void update(int id, Meal meal) {
        this.mealList.set(id,meal);
    }

    @Override
    public Meal getByid(int id) {
        return mealList.get(id);
    }
}

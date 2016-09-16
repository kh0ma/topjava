package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by kh0ma on 16.09.16.
 */
public class DAO_implementation implements DAO{
    private List<MealWithExceed> mealWithExceedList;

    public DAO_implementation() {
        this.mealWithExceedList = MealsUtil.getFilteredWithExceeded(MealsUtil.generateMeal(), LocalTime.MIN, LocalTime.MAX, 2000);
    }


    @Override
    public List<MealWithExceed> read() {
        return this.mealWithExceedList;
    }

    @Override
    public void create(MealWithExceed mealWithExceed) {
        this.mealWithExceedList.add(mealWithExceed);
    }

    @Override
    public void deleteById(int id) {
        this.mealWithExceedList.remove(mealWithExceedList.get(id));
    }

    @Override
    public void update(int id, MealWithExceed mealWithExceed) {
        this.mealWithExceedList.set(id,mealWithExceed);
    }

    @Override
    public MealWithExceed getByid(int id) {
        return mealWithExceedList.get(id);
    }
}

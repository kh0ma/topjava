package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll()
    {
        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()), LocalTime.MIN,LocalTime.MAX,AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
    {
/*        if(startDate==null) startDate = LocalDate.MIN;
        if(endDate==null) endDate = LocalDate.MAX;
        if(startTime==null) startTime = LocalTime.MIN;
        if(endTime==null) endTime = LocalTime.MAX;*/

        return getAll()
                .stream()
                .filter(mealWithExceed -> TimeUtil.isBetween(mealWithExceed.getDateTime(),startTime,endTime,startDate,endDate))
                .collect(Collectors.toList());
    }

    public Meal get(int id)
    {
        Meal meal = service.get(id);

        if(meal==null) throw new NotFoundException("(GET) nullpointer exception with id: " + id);
        else if (meal.getUserID()!=AuthorizedUser.id()) throw new NotFoundException("(GET) Invaid user exception: " + meal);

        return meal;
    }

    public void save(Meal meal)
    {
        if(meal==null) throw new NotFoundException("(SAVE/EDIT) nullpointer exception with meal: " + meal);
        else if(meal.getUserID()!=AuthorizedUser.id()) throw new NotFoundException("(SAVE/EDIT) Invaid user exception: " + meal);


        service.save(meal);
    }

    public void delete(int id)
    {
        if(get(id).getUserID()==null) throw new NotFoundException("(DELETE) nullpointer exception with id: " + id);
        else if (get(id).getUserID()!=AuthorizedUser.id()) throw new NotFoundException("(DELETE) Invaid user exception: " + id);
        service.delete(id);
    }



}

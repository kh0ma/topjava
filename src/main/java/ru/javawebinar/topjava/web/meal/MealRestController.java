package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.AuthorizedUser.setId;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Meal get(@RequestParam(value = "id") int id, @PathVariable int userId) {
        setId(userId);
        return super.get(id);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") int id, @PathVariable int userId) {
        setId(userId);
        super.delete(id);
    }

    /*
    @Override
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }*/
}

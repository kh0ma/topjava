package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    static final String REST_URL = "/rest/meals/{userId}";

    @RequestMapping(method = RequestMethod.GET)
    public void setUserId(@PathVariable int userId) {
        setId(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }
    /*
    @Override
    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }
    */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public List<MealWithExceed> getBetween(@RequestParam(value = "startDateTime", required=false) LocalDateTime startDateTime,
                                           @RequestParam(value = "endDateTime", required=false)   LocalDateTime endDateTime) {
        LocalDate startDate = startDateTime!=null ? startDateTime.toLocalDate() : null;
        LocalTime startTime = startDateTime!=null ? startDateTime.toLocalTime() : null;
        LocalDate endDate = endDateTime!=null ? endDateTime.toLocalDate() : null;
        LocalTime endTime = endDateTime!=null ? endDateTime.toLocalTime() : null;
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}

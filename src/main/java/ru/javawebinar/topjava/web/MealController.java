package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by kh0ma on 27.10.16.
 */
@Controller
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Autowired
    private MealRestController mealController;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(HttpServletRequest request, Model model) {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            model.addAttribute("meals", mealController.getAll());
            return "meals";
        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            mealController.delete(id);
            return "redirect:meals";

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = "create".equals(action) ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000) :
                    mealController.get(getId(request));
            model.addAttribute("meal", meal);
            return "meal";
        }
        return null;
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String setMeal(HttpServletRequest request, Model model) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            if (request.getParameter("id").isEmpty()) {
                LOG.info("Create {}", meal);
                mealController.create(meal);
            } else {
                LOG.info("Update {}", meal);
                mealController.update(meal, getId(request));
            }
            return "redirect:meals";

        } else if ("filter".equals(action)) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request,model));
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request,model));
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request,model));
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request,model));
            model.addAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
            return "meals";
        }


        return null;
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request, Model model) {
        String value = request.getParameter(param);
        model.addAttribute(param, value);
        return value;
    }
}

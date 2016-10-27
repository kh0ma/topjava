package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

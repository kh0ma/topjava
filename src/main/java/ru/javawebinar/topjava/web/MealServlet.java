package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by kh0ma on 13.09.16.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

//        request.getRequestDispatcher("/userList.jsp").forward(request, response);
        //response.sendRedirect("mealList.jsp");

        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsUtil.generateMeal(),LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("mealsList", mealWithExceeds);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}

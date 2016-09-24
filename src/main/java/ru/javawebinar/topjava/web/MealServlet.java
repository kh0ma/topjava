package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);


    private MealRestController service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
             service = appCtx.getBean(MealRestController.class);
         }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String userID = request.getParameter("userID");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        meal.setUserID(userID!=null&&userID!="" ? Integer.parseInt(userID):AuthorizedUser.id());
        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        service.save(meal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        /*System.out.println("++++++++++++ Start Date = " + LocalDate.parse(request.getParameter("startDate")));
        System.out.println("++++++++++++ End Date = " + LocalDate.parse(request.getParameter("endDate")));
        System.out.println("++++++++++++ Start Time = " + LocalTime.parse(request.getParameter("startTime")));
        System.out.println("++++++++++++ End Time = " + LocalTime.parse(request.getParameter("endTime")));
        */
        System.out.println("Action = " + action);

        if("filter".equals(action))
        {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");

            LOG.info("filter");
            request.setAttribute("mealList",
                    service.getAllFiltered( startDate!=null&&startDate.length()!=0?LocalDate.parse(startDate):LocalDate.MIN,
                                            startTime!=null&&startTime.length()!=0?LocalTime.parse(startTime):LocalTime.MIN,
                                            endDate!=null&&endDate.length()!=0?LocalDate.parse(endDate):LocalDate.MAX,
                                            endTime!=null&&endTime.length()!=0?LocalTime.parse(endTime):LocalTime.MAX));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if (action == null || action.length()==0) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    service.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            service.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000) :
                    service.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

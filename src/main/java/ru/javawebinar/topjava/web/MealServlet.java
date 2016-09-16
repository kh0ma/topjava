package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.DAO_implementation;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by kh0ma on 13.09.16.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);
    private static  final DAO dao = new DAO_implementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        //delete by id
        String deleteByIdString = request.getParameter("deleteById");
        Integer deleteById = null;
        if(deleteByIdString != null) {
            deleteById = Integer.parseInt(deleteByIdString);
            dao.deleteById(deleteById);
        }

        //get id by onclick row action
        String idString = request.getParameter("id");
        Integer id = null;
        if(idString != null) id = Integer.parseInt(idString);
        System.out.println(id);
        request.setAttribute("id", id);

        request.setAttribute("mealsList", dao.read());

        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}

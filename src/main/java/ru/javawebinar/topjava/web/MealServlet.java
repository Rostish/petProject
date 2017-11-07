package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");

        if(userid == null || userid.isEmpty())
        {
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), MealsUtil.getDBSize() + 1);
            MealsUtil.addMeal(meal);
        }
        else
        {
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), Integer.parseInt(userid));
            MealsUtil.updateMeal(meal);
        }
        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.getAllMeals(), LocalTime.of(0, 0),
                LocalTime.of(23, 0), 2000);
        request.setAttribute("meals", mealsWithExceeded);
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            MealsUtil.deleteMeal(userId);
        } else if (action.equalsIgnoreCase("edit")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            Meal meal = MealsUtil.getMealById(userId);
            request.setAttribute("meal", meal);
        }
        else if (action.equalsIgnoreCase("listMeals")) {
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.getAllMeals(),
                    LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
            request.setAttribute("meals", mealsWithExceeded);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}


package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Controller
public class UserMealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;


    public List<UserMealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        //log.info("getAll for Users {}",userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public UserMeal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for User {}",id, userId);
        return service.get(id);
    }

    public UserMeal create(UserMeal userMeal) {
        int userId = AuthorizedUser.id();
        log.info("create {} for User {}", userMeal, userId);
        return service.create(userMeal);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", id, userId);
        service.delete(id);
    }

    public void update(UserMeal userMeal) {
        int userId = AuthorizedUser.id();
        log.info("update {} for User {}", userMeal, userId);
        service.update(userMeal);
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate,LocalTime startTime, LocalDate endDate, LocalTime endTime){
        int userId = AuthorizedUser.id();
        //log.info("getBetween {} and {} for Users {}", startDate, endDate, userId);
        return MealsUtil.getFilteredWithExceeded(service.getBetween(startDate,endDate,userId),startTime,endTime,AuthorizedUser.getCaloriesPerDay());
    }



}
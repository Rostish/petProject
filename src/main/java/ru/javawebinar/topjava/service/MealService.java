package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    UserMeal create(UserMeal meal);

    void delete(int id) throws NotFoundException;

    UserMeal get(int id) throws NotFoundException;

    void update(UserMeal meal);

    List<UserMeal> getAll(int userId);

    default List<UserMeal> getBetween(LocalDate startDate, LocalDate endDate, int userId){
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN),LocalDateTime.of(endDate, LocalTime.MAX),userId);
    }
    List<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserMealRepository {
    UserMeal save(UserMeal meal);

    boolean delete(int id);

    UserMeal get(int id);

    Collection<UserMeal> getAll(int userId);

     Collection<UserMeal> getBetween(LocalDateTime starDateTime, LocalDateTime endDateTime, int userId);
}

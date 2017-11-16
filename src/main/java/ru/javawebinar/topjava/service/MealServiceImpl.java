package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final UserMealRepository repository;

    @Autowired
    public MealServiceImpl(UserMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserMeal create(UserMeal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public UserMeal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }


    @Override
    public void update(UserMeal meal) {
        repository.save(meal);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return (List<UserMeal>) repository.getAll(userId);
    }


    @Override
    public List<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return (List<UserMeal>) repository.getBetween(startDateTime,endDateTime.plus(1, ChronoUnit.DAYS),userId);
    }

}
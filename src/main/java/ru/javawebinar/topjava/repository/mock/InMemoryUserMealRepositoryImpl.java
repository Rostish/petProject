package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> mealRepository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        mealRepository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        UserMeal userMeal = mealRepository.get(id);
        return userMeal != null && mealRepository.remove(id) != null;
    }

    @Override
    public UserMeal get(int id) {
        return mealRepository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {

        return mealRepository.values().stream().filter(userMeal -> userMeal.getUserId()==userId).collect(Collectors.toList());
    }

    public Collection<UserMeal> getBetween(LocalDateTime starDateTime, LocalDateTime endDateTime, int userId){
        Objects.requireNonNull(starDateTime);
        Objects.requireNonNull(endDateTime);
        return getAll(userId).stream()
                .filter(userMeal -> DateTimeUtil.isBetweenDays(userMeal.getDateTime(),starDateTime,endDateTime))
                .collect(Collectors.toList());
    }
}


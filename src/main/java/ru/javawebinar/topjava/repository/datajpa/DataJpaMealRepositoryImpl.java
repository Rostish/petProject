package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {
    private static final Sort SORT_MEALS_USERID = new Sort(Sort.Direction.ASC, "id","user_id");

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    public Meal save(Meal meal, int userId) {

        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userId);
            meal.setUser(user);
            return crudRepository.save(meal);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id,userId)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getByIdAndUserId(id,userId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(SORT_MEALS_USERID);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.findByDateTimeBetweenAndUserIdOrderByDateTimeDesc(startDate,endDate,userId);
    }
}

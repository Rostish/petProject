package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
    @Autowired
    MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(MEAL_ID,USER_ID);
        assertMatch(meal, MEAL);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(MEAL_ID,USER_ID);
        final List<Meal> MEALS = Arrays.asList(
                new Meal(100004,LocalDateTime.of(2015, Month.MAY, 31, 12, 0), "Dinner", 500),
                new Meal(100003,LocalDateTime.of(2015, Month.MAY, 31, 11, 0), "Dinner", 510)
        );
        assertThat(mealService.getAll(USER_ID)).isEqualTo(MEALS);
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(USER_ID);
        assertThat(all).isEqualTo(MEALS);
    }
    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL);
        updated.setCalories(1000);
        updated.setDescription("Dinner");
        mealService.update(updated,100000);
        assertMatch(mealService.get(100002, USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(100008,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal createMeal = mealService.create(newMeal,100000);
        assertThat(newMeal).isEqualTo(createMeal);
    }

}
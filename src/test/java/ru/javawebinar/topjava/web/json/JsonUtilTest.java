package ru.javawebinar.topjava.web.json;

import org.junit.Test;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ADMIN_MEAL1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, ADMIN_MEAL1);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, MEALS);
    }
}
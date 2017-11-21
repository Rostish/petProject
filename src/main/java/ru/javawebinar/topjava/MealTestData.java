package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = 100002;

    public static final Meal MEAL =  new Meal(100002,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Dinner", 500);
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100004,LocalDateTime.of(2015, Month.MAY, 31, 12, 0), "Dinner", 500),
            new Meal(100003,LocalDateTime.of(2015, Month.MAY, 31, 11, 0), "Dinner", 510),
            new Meal(100002,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Dinner", 520)
    );

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}

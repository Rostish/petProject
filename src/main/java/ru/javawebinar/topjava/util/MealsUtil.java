package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static final List<UserMeal> MEALS = Arrays.asList(
            new UserMeal(1,1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(2,2,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new UserMeal(3,1,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new UserMeal(4,2,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new UserMeal(5,1,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new UserMeal(6,2,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<UserMealWithExceed> getWithExceeded(Collection<UserMeal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(Collection<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories))
//                      Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    private static UserMealWithExceed createWithExceed(UserMeal meal, boolean exceeded) {
        return new UserMealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }

/*
    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<UserMealWithExceed> mealsWithExceeded = new ArrayList<>();
        meals.forEach(meal -> {
            if (DateTimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealsWithExceeded.add(createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededInOnePass(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Collection<List<UserMeal>> list = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate)).values();

        return list.stream().flatMap(dayMeals -> {
            boolean exceed = dayMeals.stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay;
            return dayMeals.stream().filter(meal ->
                    DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .map(meal -> createWithExceed(meal, exceed));
        }).collect(toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededInOnePass2(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final class Aggregate {
            private final List<UserMeal> dailyMeals = new ArrayList<>();
            private int dailySumOfCalories;

            private void accumulate(UserMeal meal) {
                dailySumOfCalories += meal.getCalories();
                if (DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    dailyMeals.add(meal);
                }
            }

            // never invoked if the upstream is sequential
            private Aggregate combine(Aggregate that) {
                this.dailySumOfCalories += that.dailySumOfCalories;
                this.dailyMeals.addAll(that.dailyMeals);
                return this;
            }

            private Stream<UserMealWithExceed> finisher() {
                final boolean exceed = dailySumOfCalories > caloriesPerDay;
                return dailyMeals.stream().map(meal -> createWithExceed(meal, exceed));
            }
        }

        Collection<Stream<UserMealWithExceed>> values = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate,
                        Collector.of(Aggregate::new, Aggregate::accumulate, Aggregate::combine, Aggregate::finisher))
                ).values();

        return values.stream().flatMap(identity()).collect(toList());
    }
*/
}
package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {

    private static final Map<Integer, Meal> DB = new HashMap<>();
    private static List<Meal> meals = new ArrayList<>();
    static {
        initDB();
    }

    public static void main(String[] args) {

    }

    public static void initDB() {
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500,1),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000,2),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500,3),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000,4),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500,5),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510,6)
        );

        for (int i = 0; i < meals.size(); i++) {
            DB.put(meals.get(i).getUserid(), meals.get(i));
        }

    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }


    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded, meal.getUserid());
    }

    public static void addMeal(Meal meal) {
        DB.put(meal.getUserid(), meal);
    }

    public static void updateMeal(Meal meal) {
        DB.put(meal.getUserid(), meal);
    }

    public static void deleteMeal(int userid) {
        DB.remove(userid);
    }

    public static List<Meal> getAllMeals() {
        Collection<Meal> c = DB.values();
        List<Meal> list = new ArrayList<>();
        list.addAll(c);

        return list;
    }

    public static Meal getMealById(int userid) {

        return DB.get(userid);
    }

    public static int getDBSize(){

        return DB.size();
    }
}
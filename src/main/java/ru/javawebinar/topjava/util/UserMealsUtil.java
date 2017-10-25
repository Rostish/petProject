package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 15,10,0), "Завтрак", 500), //false-0
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 14,13,0), "Обед", 1000), //false-1
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 13,20,0), "Ужин", 500), //false-2
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 12,10,0), "Завтрак", 1000),//false-3
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 11,13,0), "Обед", 500),//false-4
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 10,20,0), "Ужин", 2010),//true-5
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 9,10,0), "Завтрак", 1000),//false-6
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 8,13,0), "Обед", 500),//false-7
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 7,20,0), "Ужин", 2100),//true-8
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 6,10,0), "Завтрак", 1000),//false-9
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 5,13,0), "Обед", 2500),//true-10
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 4,20,0), "Ужин", 500),//false-11
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 3,10,0), "Завтрак", 500),//false-12
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 2,13,0), "Обед", 1000),//-false13
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 1,20,0), "Ужин", 2500),//true-14
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 24,10,0), "Завтрак", 1000),//false-15
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 23,13,0), "Обед", 500),//false-16
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 22,20,0), "Ужин", 510),//false-17
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 21,10,0), "Завтрак", 1000),//false-18
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 20,13,0), "Обед", 500),//false-19
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 19,20,0), "Ужин", 2100),//true-20
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 18,10,0), "Завтрак", 2000),//false-21
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 17,13,0), "Обед", 500),//false-22
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 16,20,0), "Ужин", 500),//false-23
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),//false-24
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 1000),//false-25
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29,13,0), "Обед", 500),//false-26
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28,20,0), "Ужин", 2100),//true-27
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 27,10,0), "Завтрак", 2000),//false-28
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 26,13,0), "Обед", 500),//false-29
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 25,20,0), "Ужин", 500)//false-30

        );
        getFilteredWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(23,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> result;
        int sumForDay = mealList.get(0).getCalories();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean [] isExceed = new boolean[32];

        for (int i = 0; i < mealList.size()-1; i++) {
            if (mealList.get(i).getDateTime().format(formatter).equals(mealList.get(i+1).getDateTime().format(formatter))) {
                sumForDay = sumForDay + mealList.get(i+1).getCalories();
            }
            else sumForDay = mealList.get(i+1).getCalories();
            if(sumForDay>caloriesPerDay){
                isExceed[mealList.get(i).getDateTime().getDayOfMonth()-1] = true;
            }
        }

        result = mealList.stream()
                .filter(aMealList -> (aMealList.getDateTime().toLocalTime().isBefore(endTime)) && (aMealList.getDateTime().toLocalTime().isAfter(startTime)))
                .map(aMealList -> new UserMealWithExceed(aMealList.getDateTime(), aMealList.getDescription(), aMealList.getCalories(), isExceed[aMealList.getDateTime().getDayOfMonth()]))
                .collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            UserMealWithExceed userMealWithExceed = result.get(i);
            System.out.println(i+": "+userMealWithExceed);
        }


        return result;

    }
}

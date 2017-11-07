package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Meal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final int userid;

    public Meal(LocalDateTime dateTime, String description, int calories, int userid) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userid = userid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public int getUserid() {
        return userid;
    }
}

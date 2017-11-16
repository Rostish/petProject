package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class UserMeal extends AbstractBaseEntity {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final Integer userId;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null,null, dateTime, description, calories);
    }

    public UserMeal(Integer id, Integer userId, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", UserId="+userId+
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

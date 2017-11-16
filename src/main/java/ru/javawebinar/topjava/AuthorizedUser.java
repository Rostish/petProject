package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;

import java.util.Collections;
import java.util.Set;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {


    protected int id = 2;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = true;

    private static AuthorizedUser AUTORIZED_USER = new AuthorizedUser();

    public static AuthorizedUser get() {
        return AUTORIZED_USER;
    }

    public static int id() {
        return get().id;
    }

    public Set<Role> getAuthorities(){return roles;}

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.Collection;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    Collection<User> getAll();
}
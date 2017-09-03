package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getBetween(LocalDateTime date1, LocalDateTime date2, int userId);
}

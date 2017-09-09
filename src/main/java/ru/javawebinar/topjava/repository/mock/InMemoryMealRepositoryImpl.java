package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.mock.InMemoryMockUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryMockUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Comparator<Meal> USER_MEAL_COMPARATOR
            = (um1,um2) -> um2.getDateTime().compareTo(um1.getDateTime());

    private Map<Integer,Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ Ланч", 510), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ Ужин", 510), ADMIN_ID);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer, Meal> userMeal = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        userMeal.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        return userMeal != null && userMeal.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        return userMeal == null ? null : userMeal.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.get(userId)
                .values()
                .stream()
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime date1, LocalDateTime date2, int userId) {
        return repository.get(userId).values().stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), date1, date2))
                .sorted(USER_MEAL_COMPARATOR).collect(Collectors.toList());
    }
}


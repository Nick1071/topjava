package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public Meal get (int id){
        int userId = AuthorizedUser.id();
        LOG.info("get meel {} for user {}", id, userId);
        return service.get(id, userId);
    };

    public void delete(int id){
        int userId = AuthorizedUser.id();
        LOG.info("delete meel {} for user {}", id, userId);
        service.delete(id, userId);
    };

    public List<Meal> getAll(){
        int userId = AuthorizedUser.id();
        LOG.info("get all meel for user {}", userId);
        return service.getAll(userId);
    };

    public void deleteAll(){
        int userId = AuthorizedUser.id();
        LOG.info("delete all meel for user {}", userId);
        service.deletteAll(userId);
    }

    public Meal update(Meal meal){
        int userId = AuthorizedUser.id();
        LOG.info("update meel {} for user {}", meal, userId);
        return service.update(meal, userId);
    };

    public Meal create(Meal meal){
        int userId = AuthorizedUser.id();
        LOG.info("create meel {} for user {}", meal, userId);
        return service.save(meal, userId);
    };

    public List<Meal> getBetween(LocalDate startDate, LocalDate endDate){
        int userId = AuthorizedUser.id();
        LOG.info("get meel between {} and {} for user {}", startDate, endDate, userId);
        return service.getBetweenDates(startDate, endDate, userId);

    }


}
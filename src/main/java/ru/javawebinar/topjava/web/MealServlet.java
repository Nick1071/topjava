package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryUserMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private InMemoryUserMealRepository repository = new InMemoryUserMealRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            log.debug("Show all");
            List<UserMealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(repository.getAll(), null, null, 2000);
            req.setAttribute("meals", mealsWithExceeded);
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
            return;
        }
        if (action.equals("delete")){
            String id = req.getParameter("id");
            log.debug("Delete {}", id);
            repository.delete(Integer.parseInt(id));
            resp.sendRedirect("meals");
            return;
        }
        if (action.equals("creat")){
            log.debug("Forvard to creatMeal.jsp");
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime=localDateTime.minusSeconds(localDateTime.getSecond());
            final UserMeal userMeal = new UserMeal(localDateTime, "", 1000);
            req.setAttribute("newMeal", userMeal);
            req.getRequestDispatcher("creatMeal.jsp").forward(req,resp);
            return;
        }
        if (action.equals("update")){
            String id = req.getParameter("id");
            log.debug("Forvard to creatMeal.jsp");
            final UserMeal userMeal = repository.get(Integer.parseInt(id));
            req.setAttribute("newMeal", userMeal);
            req.getRequestDispatcher("creatMeal.jsp").forward(req,resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        final UserMeal userMeal = new UserMeal(id.isEmpty()? null : Integer.parseInt(id),
                LocalDateTime.parse(req.getParameter("DateTime")), req.getParameter("Description"),
                        Integer.parseInt(req.getParameter("Calories")));
        log.debug(id.isEmpty()? "Creat Meal {}" : "Update Meal{}", userMeal);
        repository.save(userMeal);
        resp.sendRedirect("meals");
    }
}

<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="1">
    <tr>
        <td>
            Date
        </td>
        <td>
            Description
        </td>
        <td>
            Calories
        </td>
    </tr>

    <% for(MealWithExceed meal : (List<MealWithExceed>)request.getAttribute("meals")){%>

    <tr style = <% if(meal.isExceed()) {%> "color:red" <%} else {%> "color:green"<%}%>>
        <td>
            <%= meal.getDateTime().toLocalDate() + " " + meal.getDateTime().toLocalTime()%>
        </td>
        <td>
            <%= meal.getDescription()%>
        </td>
        <td>
            <%= meal.getCalories()%>
        </td>
    </tr><%}%>

</table>
</body>
</html>

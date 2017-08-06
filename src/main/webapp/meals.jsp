<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
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

    <% for(Meal meal : MealsUtil.meals){%>

    <tr>
        <td>
            <%= meal.getDateTime()%>
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

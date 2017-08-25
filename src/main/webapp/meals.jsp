<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .s1 {
            color: green
        }

        .s2 {
            color: red
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meal list</h2>


<h3><a href="meals?action=creat">Add Meal</a></h3>

<table border="1">
    <thead>
    <tr>
        <td>
            Id
        </td>
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
    </thead>

    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed ? 's2':'s1'} ">
            <td>
                ${meal.id}
            </td>
            <td>
                    ${meal.dateTime}
            </td>
            <td>
                    ${meal.description}
            </td>
            <td>
                    ${meal.calories}
            </td>
            <td>
                <a href="meals?action=update&id=${meal.id}">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=${meal.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>

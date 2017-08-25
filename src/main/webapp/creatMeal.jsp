<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html5>
<head>
    <title>Edit User</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Edit Meal</h2>

<jsp:useBean id="newMeal" scope="request" type="ru.javawebinar.topjava.model.UserMeal"/>

<form action="meals" method="post">
    <input type="hidden" value="${newMeal.id}" name="id">
    <dl>
        <dt>DateTime</dt>
        <dd><input type="datetime-local" value="${newMeal.dateTime}" name="DateTime"></dd>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd><input type="text" value="${newMeal.description}" name="Description"></dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd><input type="number" value="${newMeal.calories}" name="Calories"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html5>
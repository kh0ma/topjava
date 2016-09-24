<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Meal list</h3>
    <form action="meals">
        <input type="hidden" name="action" value="filter">
        <fieldset style="width: 500px;">
            <legend>Filters</legend>
            <table>
            <tr>
                <td>
                    <label for="startDate">Start Date: </label>
                    <input type="date" id="startDate" name="startDate">
                </td>
                <td>
                    <label for="endDate">End Date: </label>
                    <input type="date" id="endDate" name="endDate">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="startTime">Start Time: </label>
                    <input type="time" id="startTime" name="startTime">
                </td>
                <td>
                    <label for="endTime">End Time: </label>
                    <input type="time" id="endTime" name="endTime">
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td style="text-align: right">
                    <button type="reset" onclick="location.href='meals?action='">Clear</button>
                    <button type="submit">Filter</button>
                </td>
            </tr>
            </table>
        </fieldset>
    </form>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
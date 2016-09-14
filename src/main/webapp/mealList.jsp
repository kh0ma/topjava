<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kh0ma
  Date: 13.09.16
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <tr>
        <th align="left">Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealsList}" var="item">
        <c:set var="trcolor" scope="session" value="color:#00FF00"/>
        <c:if test="${item.isExceed()}">
            <c:set var="trcolor" scope="session" value="color:#FF0000"/>
        </c:if>
        <tr style="${trcolor}">
            <td>
                <c:out value="${item.getDateTime()}" />
            </td>
            <td>
                <c:out value="${item.getDescription()}" />
            </td>
            <td>
                <c:out value="${item.getCalories()}" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

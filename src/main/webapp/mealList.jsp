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
<h2>

    <%
        List list = MealsUtil.generateMeal();
    %>

    <%
        for (int i = 0; i < list.size(); i++) { %>
    <%=list.get(i)%>
       <% }%>





</h2>
<h2>
    <c:out value="${MealsUtil.generateMeal().get(0)}" />
</h2>

<table>
    <!-- here should go some titles... -->
    <tr>
        <th align="left">Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${MealsUtil.generateMeal()}" var="item">
        <tr>
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

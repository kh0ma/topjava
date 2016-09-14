<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="java.util.List" %>
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
</body>
</html>

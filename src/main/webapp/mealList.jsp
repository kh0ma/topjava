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
    <style>
        html {
            font-family: sans-serif;
            padding: 2em;
        }

        table {
            border-spacing: 0;
        }
        th {
            font-weight: bold;
            text-transform: uppercase;
            text-align: left;
            color: #215;
            padding: 5px 10px;
        }

        td {
            border-top: 1px solid #e8edff;
            padding: 5px 10px;
        }

        td a {
            text-decoration: none;
        }

        tr.meal-row {
            cursor: crosshair;
        }
        tr.meal-row:hover {
            background-color: #ecdcff;
        }

        .formBlock {
            width: 250px;
            /*background: #ffffff;*/
            padding: 5px;
            padding-right: 20px;
            padding-top: 20px;
            /*border: solid 1px black;*/
            font-weight: bold;

        }

        fieldset
        {
            padding-top: 5px;
            font-size: 16px;
        }
        button {
            background-color: white;
            border: 2px solid black;
            color: black;
            padding: 5px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            color: black;
            border-radius: 15px;

        }

        button:hover {
            background-color: black; /* Green */
            color: white;
        }
        button:active {
            background-color: #d1b8ff;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<button>Add meal</button>
<button>Edit meal</button>
<button onclick="location.href='meals?deleteById=${id}'">Delete meal</button>
<br>
<c:if test="${formTrigger}">
    <div class= formBlock>
        <form action="meals">
            <fieldset>
                <legend>Add meal</legend>
                Description: <br><input type="text" name="decription"><br>
                Date and time: <br><input type="datetime-local" name="daytime"><br>
                Calories: <br><input type="number" name="calories"><br>
                <br><button class="submit-button" type="submit">Save</button>
                <button class="cancel-button">Cancel</button>
            </fieldset>
        </form>
    </div>
    <br>
</c:if>
<table>
    <tr bgcolor="#ffe4c4">
        <th align="left">Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealsList}" var="item" varStatus="loop">
        <c:set var="trcolor" scope="session" value="color:#00FF00"/>
        <c:if test="${item.isExceed()}">
            <c:set var="trcolor" scope="session" value="color:#FF0000"/>
        </c:if>
        <c:set var="trBGcolor" scope="session" value="#FFFFFF"/>
        <c:if test="${id == loop.index}"> <c:set var="trBGcolor" scope="session" value="#cabadd"/> </c:if>
        <tr style="${trcolor}" bgcolor="${trBGcolor}" onclick="location.href='meals?id=${loop.index}'" class="meal-row normal">
            <td>
                <c:out value="${item.getDateTime().toLocalDate()} ${item.getDateTime().toLocalTime()}" />
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Votes</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Votes</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Restaurant</th>
        <th>Votes for restaurant</th>
        <th>Total votes</th>
    </tr>
    </thead>
    <c:forEach items="${votes}" var="vote">
        <jsp:useBean id="vote" scope="page" type="ru.alvisid.votingsystem.TO.VoteForRestaurant"/>
        <tr>
            <fmt:parseDate value="${vote.date}" pattern="yyyy-MM-dd" var="parsedDate" type="both" />
            <td>
                <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedDate }" />
            </td>
            <td>${vote.restaurantName}</td>
            <td>${vote.totalVotesForRestaurantADay}</td>
            <td>${vote.totalVotesForADay}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
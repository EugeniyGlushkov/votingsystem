<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setBundle basename="messages.app"/>--%>

<html>
<head>
    <title>Votes</title>
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/"><fmt:message key="app.home"/></a></h3>
<h2><fmt:message key="vote.title"/></h2>
<form method="post" action="votes">
    <dl>
        <dt><fmt:message key="datefilter.from"/></dt>
        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
    </dl>
    <dl>
        <dt><fmt:message key="datefilter.to"/></dt>
        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
    </dl>
    <button type="submit"><fmt:message key="filter.filter"/></button><button type="reset"><fmt:message key="filter.reset"/></button>
</form>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th><fmt:message key="vote.date"/></th>
        <th><fmt:message key="vote.restaurant"/></th>
        <th><fmt:message key="vote.votesrest"/></th>
        <th><fmt:message key="votes.total"/></th>
    </tr>
    </thead>
    <c:forEach items="${votes}" var="vote">
        <jsp:useBean id="vote" scope="page" type="ru.alvisid.votingsystem.to.RestaurantVotes"/>
        <tr>
            <fmt:parseDate value="${vote.date}" pattern="yyyy-MM-dd" var="parsedDate" type="both"/>
            <td>
                <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedDate}"/>
            </td>
            <td>${vote.restaurantName}</td>
            <td>${vote.totalVotesForRestaurantADay}</td>
            <td>${vote.totalVotesForADay}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
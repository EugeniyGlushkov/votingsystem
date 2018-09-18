<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<fmt:setBundle basename="messages.app"/>--%>

<html>
<head>
    <title>Votes</title>
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a></h3>
<h2><spring:message code="vote.title"/></h2>
<form method="post" action="votes">
    <dl>
        <dt><spring:message code="datefilter.from"/></dt>
        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
    </dl>
    <dl>
        <dt><spring:message code="datefilter.to"/></dt>
        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
    </dl>
    <button type="submit"><spring:message code="filter.filter"/></button><button type="reset"><spring:message code="filter.reset"/></button>
</form>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th><spring:message code="vote.date"/></th>
        <th><spring:message code="vote.restaurant"/></th>
        <th><spring:message code="vote.votesrest"/></th>
        <th><spring:message code="votes.total"/></th>
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
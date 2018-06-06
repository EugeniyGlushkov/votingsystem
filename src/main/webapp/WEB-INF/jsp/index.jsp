<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<fmt:setBundle basename="messages.app"/>--%>

<html>
<head>
    <title>Java Enterprise (VotingSystem)</title>
</head>
<body>
<h3><spring:message code="app.title"/> </h3>
<hr>
<ul>
    <li><a href="votes"><spring:message code="vote.title"/></a></li>
</ul>
</body>
</html>

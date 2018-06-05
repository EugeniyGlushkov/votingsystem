<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setBundle basename="messages.app"/>--%>

<html>
<head>
    <title>Java Enterprise (VotingSystem)</title>
</head>
<body>
<h3><fmt:message key="app.title"/> </h3>
<hr>
<ul>
    <li><a href="votes"><fmt:message key="vote.title"/></a></li>
</ul>
</body>
</html>

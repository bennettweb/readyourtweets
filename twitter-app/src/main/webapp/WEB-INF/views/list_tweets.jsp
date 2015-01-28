<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="/resources/css/style.css" />
</head>
<body>
<h2><spring:message code="tweets.header" /></h2>
<ul class="tweets">
    <c:forEach items="${tweets}" var="tweet">
        <li>${tweet}</li>
    </c:forEach>
</ul>
</body>
</html>
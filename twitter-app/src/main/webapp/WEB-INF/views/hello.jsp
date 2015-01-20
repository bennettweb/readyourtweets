<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="/resources/css/style.css" />
</head>
<body>
<h2><spring:message code="hello" /> <c:out value="${name}" />!</h2>
</body>
</html>
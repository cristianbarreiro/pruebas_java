<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>This is beta page</h1>
    <h2>Brand: ${mylist.brand}</h2>
    <ul>
        <c:forEach var="device" items="${mylist.devices}">
            <li>${device.name} - ${device.description}</li>
        </c:forEach>
    </ul>
</body>
</html>
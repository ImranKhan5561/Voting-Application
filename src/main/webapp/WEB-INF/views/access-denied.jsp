<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<h1 style="color:red;">${errorMessage}</h1>
    <h2>Access Denied</h2>
    <p>You do not have permission to access this page.</p>
    <a href="/welcome">Go back to the homepage</a>
</body>
</html>

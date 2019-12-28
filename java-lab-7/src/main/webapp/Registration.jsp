<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration page</h1>
<form method="post" action="/main/registration">
    <input name="login" type="text" required>
    <input name="password" type="password" required>
    <button type="submit">Sign up</button>
</form>
</body>
</html>

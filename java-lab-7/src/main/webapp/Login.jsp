<%--
  Created by IntelliJ IDEA.
  User: Dinar Shakurov
  Date: 28.12.2019
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<%--<c:if test="${requestScope.status == 'incorrect'}">
    <h2>Incorrect login or password, try again</h2>
</c:if>--%>
<form method="post" action="/main/login">
    <input name="login" type="text" required>
    <input name="password" type="password" required>
    <button type="submit">Sign in</button>
</form>
</body>
</html>

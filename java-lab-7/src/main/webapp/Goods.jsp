
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>
</head>
<body>
<form action="/goods" method="post">
    <input type="hidden" name="form_type" value="buy">
    <input type="text" name="good_id" placeholder="Buy">
    <input type="submit" value="Buy">
</form>
<form action="/goods" method="post">
    <input type="hidden" name="form_type" value="add">
    <input type="text" name="good_name" placeholder="Add">
    <input type="number" name="good_price" placeholder="Price">
    <input type="submit" value="Add">
</form>
<c:if test="${myGoods != null}">
    <h2>My goods</h2>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="good" items="${myGoods}">
            <tr>
                <td>${good.name}</td>
                <td>${good.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<hr>
<hr>
<hr>
<hr>
<c:if test="${allGoods != null}">
    <h2>All goods</h2>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="good" items="${allGoods}">
            <tr>
                <td>${good.name}</td>
                <td>${good.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>ngocbinh website</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
<div style="text-align: center;">
    <h1>ADD NEW CATEGORY</h1> <a href="home">Home</a>
    <form action="add" method="POST">
        <h3>Name: <input type="text" name="name"></h3>
        <h3>Category: <input type="text" name="category"></h3>
        <h3>National: <input type="text" name="national"></h3>
        <h3>Value: <input type="number" name="value"></h3>
        <h3>Amount: <input type="number" name="amount"></h3>
        <button>Add</button>
    </form>
    <c:if test="${statusAdd != null}">
        <h3 style="text-align: center">${statusAdd}</h3>
    </c:if>
</div>
</body>

</html>
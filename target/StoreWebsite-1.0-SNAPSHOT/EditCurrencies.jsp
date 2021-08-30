<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 3:23 PM
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
<h1 style="text-align: center;">EDIT INFORMATION ID = ${currencies.getId()}</h1>

<div style="text-align: center;">
    <a style="text-align: center;" href="home">Home</a>
    <form action="edit?id=${currencies.getId()}" method="POST">
        <h3>Name: <input type="text" name="pName" value="${currencies.getName()}"></h3>
        <h3>Category: <input type="text" name="pCategory" value="${currencies.getCategoryName()}"></h3>
        <h3>National: <input type="text" name="pNational" value="${currencies.getNational()}"></h3>
        <h3>Value: <input type="number" name="pValue" value="${currencies.getValueCurrencies()}"></h3>
        <h3>Amount: <input type="number" name="pAmount" value="${currencies.getAmount()}"></h3>
        <button>Edit</button>
    </form>
    <c:if test="${statusEdit != null}">
        <h3>${statusEdit}</h3>
    </c:if>
</div>


</body>

</html>

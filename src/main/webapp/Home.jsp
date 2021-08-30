<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>

<head>
    <title>ngocbinh website</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
    <h1 style="text-align: center"> QUAN LY DANH SACH TIEN </h1>
    <h3 style="text-align: center;">Hello ${username}. <a href="logout">logout</a></h3>
    <h1 style="text-align: center">
        <a href="add">Add New Money Category</a>
    </h1>

    <div style="display: flex; justify-content: center;">
        <table border="2" style="left: 50%; text-align: center;">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>CATEGORY</th>
                <th>NATIONAL</th>
                <th>VALUE</th>
                <th>AMOUNT</th>
                <th>ACTION</th>
                <th>VIEW</th>
            </tr>

            <c:forEach var="currencies" items="${listCurrencies}">
                <tr>
                    <td>${currencies.getId()}</td>
                    <td>${currencies.getName()}</td>
                    <td>${currencies.getCategoryName()}</td>
                    <td>${currencies.getNational()}</td>
                    <td>${currencies.getValueCurrencies()}</td>
                    <td>${currencies.getAmount()}</td>
                    <td>
                        <a href="delete?id=${currencies.getId()}">delete</a>
                        <a href="edit?id=${currencies.getId()}">edit</a>
                    </td>
                    <td>
                        <a href="history-edit?id=${currencies.getId()}">history edit</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>

</body>

</html>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 3:31 PM
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
<div style="text-align: center">
    <h1>HISTORY UPDATE ID = ${historyId}</h1>
    <a href="home">Home</a>
</div>
<div>
    <h1></h1>
</div>
<div style="display: flex; justify-content: center;">
    <table border="2" style="left: 50%; text-align: center;">
        <tr>
            <th>NAME</th>
            <th>CATEGORY</th>
            <th>NATIONAL</th>
            <th>VALUES</th>
            <th>AMOUNT</th>
            <th>DATE EDIT<h5>(dd/mm/yy)</h5></th>
        </tr>

        <c:forEach var="record" items="${listRecord}">
            <tr>
                <td>${record.getName()}</td>
                <td>${record.getCategoryName()}</td>
                <td>${record.getNational()}</td>
                <td>${record.getValueCurrencies()}</td>
                <td>${record.getAmount()}</td>
                <td>${record.getDateFormat()}</td>
            </tr>
        </c:forEach>


    </table>
</div>


</body>

</html>
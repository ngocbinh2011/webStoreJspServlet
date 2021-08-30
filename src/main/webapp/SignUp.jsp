<%@ page import="com.webapp.Util.Common" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 3:04 PM
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
<h1 style="text-align: center;">SIGN UP</h1>
<div>
    <form action="sign-up" method="POST" style="text-align: center">
        <p>username: <input type="text" name="usernameSignUp" id=""></p>
        <p>password: <input type="password" name="passwordSignUp" id=""></p>
        <p style="padding-right: 65px;">xac nhan password: <input type="password" name="passwordConfirm" id=""></p>
        <button>submit</button>
    </form>

    <c:if test="${statusSignUp != null}">
        <h3 style="text-align: center">${statusSignUp}</h3>
    </c:if>
</div>

</body>

</html>
<%@ page import="com.webapp.Util.Common" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/27/2021
  Time: 3:01 PM
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
<div>
    <h1 style="text-align: center;">SIGN IN</h1>
    <form action="login" method="POST" style="text-align: center">
        <p>username: <input type="text" name="username" id=""></p>
        <p>password: <input type="password" name="password" id=""></p>
        <input type="submit" name="" id="">
        <a href="sign-up">sign up</a>
    </form>
    <%
        Object object = request.getAttribute(Common.STATUS_LOGIN_VARIABLE);
        String statusLogin = null;
        if(object != null){
            statusLogin = (String) object;
        }
    %>
    <c:if test="${statusLogin != null}">
        <h3 style="text-align: center">${statusLogin}</h3>
    </c:if>
</div>


</body>

</html>
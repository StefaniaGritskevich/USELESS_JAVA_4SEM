<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.04.2024
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2><%= "Sign in" %></h2>
<form method="post" action="user-servlet">
    Login <input type="text" name="login"/> <br/> <br/>
    Password <input type="text" name="password"/> <br/> <br/>
    <input type="submit" value="Войти"/>
</form>
</body>
</html>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<div style="text-align: center;"><font size="4" style="font-size: 14pt;"><b>Войдите в систему</b></font></div>
<form action="/pages/login" method="post">
    <p style="text-align: center;">
        <label for="login">Логин:</label>
        <input type="text" name="login" id="login" value="Labusyuk">
    </p>

    <p style="text-align: center;">
        <label for="password">Пароль:</label>
        <input type="password" name="password" id="password" value="password">
    </p>

    <p class="login-submit" style="text-align: center;">
            <input type="submit" value="Войти" /><br>
    </p>
    <div style="text-align: center;">${message}</div>
</form>
</body>
</html>

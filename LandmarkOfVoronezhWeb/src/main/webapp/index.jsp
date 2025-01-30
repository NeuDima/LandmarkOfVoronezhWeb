<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход пользователя</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>

    <div class="container">
        <form action="loginServlet" method="post">
            <div class="div--errorMessage">
                <c:if test="${not empty errorLoginOrPassword}">
                    <span>${errorLoginOrPassword}</span>
                </c:if>
            </div>
            <div class="input-group">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" placeholder="Введите логин" required>
            </div>
            <div class="input-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Введите пароль" required>
            </div>
            <button class="button--input" type="submit">Войти</button>
        </form>
        <div style="text-align: center;">
            <a class="link--registerAndLogin" href="register.jsp">Зарегестрироваться</a>
        </div>
    </div>

</body>
</html>
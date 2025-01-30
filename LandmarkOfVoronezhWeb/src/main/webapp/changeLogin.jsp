<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Настройки профиля</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>

    <div class="container">
        <form action="changeLogin" method="post">
            <div class="div--errorMessage">
                <c:if test="${not empty errorNewLogin}">
                    <span>${errorNewLogin}</span>
                </c:if>
            </div>
            <div class="input-group">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" placeholder="Введите новый логин" required>
            </div>
            <div class="input-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Введите пароль" required>
            </div>
            <button class="button--input" type="submit">Изменить логин</button>
        </form>
    </div>
</body>
</html>

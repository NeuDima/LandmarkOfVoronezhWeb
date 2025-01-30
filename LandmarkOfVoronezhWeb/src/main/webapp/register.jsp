<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>

    <div class="container">
        <form action="registerServlet" method="post">
            <div class="div--errorMessage">
                <c:if test="${not empty errorLoginIsAlreadyInUse}">
                    <span>${errorLoginIsAlreadyInUse}</span>
                </c:if>
            </div>
            <div class="div--errorMessage">
                <c:if test="${not empty errorNotEqualsPassword}">
                    <span>${errorNotEqualsPassword}</span>
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
            <div class="input-group">
                <label for="passwordRepeat">Пароль</label>
                <input type="password" id="passwordRepeat" name="passwordRepeat" placeholder="Повторите пароль" required>
            </div>
            <button class="button--input" type="submit">Зарегестрироваться</button>
        </form>
        <div style="text-align: center;">
            <a class="link--registerAndLogin" href="index.jsp">Войти</a>
        </div>
    </div>

</body>
</html>
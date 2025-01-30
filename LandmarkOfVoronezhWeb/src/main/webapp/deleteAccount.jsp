<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Настройки профиля</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>

<div class="container">
    <form action="deleteAccount" method="post">
        <div class="div--errorMessage">
            <c:if test="${not empty deleteAccount}">
                <span>${deleteAccount}</span>
            </c:if>
        </div>
        <div class="input-group">
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" placeholder="Введите пароль" required>
        </div>
        <button class="button--input--delete" type="submit">Удалить аккаунт</button>
    </form>
</div>
</body>
</html>

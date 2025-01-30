<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Настройки профиля</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>

<div class="container">
    <form action="changePassword" method="post">
        <div class="div--errorMessage">
            <c:if test="${not empty errorNewPassword}">
                <span>${errorNewPassword}</span>
            </c:if>
        </div>
        <div class="input-group">
            <label for="passwordOld">Старый пароль</label>
            <input type="password" id="passwordOld" name="passwordOld" placeholder="Введите старый пароль" required>
        </div>
        <div class="input-group">
            <label for="passwordNew">Новый пароль</label>
            <input type="password" id="passwordNew" name="passwordNew" placeholder="Введите новый пароль" required>
        </div>
        <div class="input-group">
            <label for="passwordNewRepeat">Повторите новый пароль</label>
            <input type="password" id="passwordNewRepeat" name="passwordNewRepeat" placeholder="Повторите новый пароль" required>
        </div>
        <button class="button--input" type="submit">Изменить пароль</button>
    </form>
</div>
</body>
</html>

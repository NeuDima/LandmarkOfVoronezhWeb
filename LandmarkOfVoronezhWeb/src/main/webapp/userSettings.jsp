<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Настройки профиля</title>
    <link rel="stylesheet" href="./CSS/authorization.css">
</head>
<body>
    <div class="container">
        <form action="changeLogin" method="get">
            <button class="button--input" type="submit">Изменить логин</button>
        </form>
        <form action="changePassword" method="get">
            <button class="button--input" type="submit">Изменить пароль</button>
        </form>
        <form action="deleteAccount" method="get">
            <button class="button--input--delete" type="submit">Удалить аккаунт</button>
        </form>
    </div>
</body>
</html>

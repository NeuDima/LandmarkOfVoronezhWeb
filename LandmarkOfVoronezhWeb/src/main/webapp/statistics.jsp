<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Статистика</title>
    <link rel="stylesheet" type="text/css" href="./CSS/statisticsStyle.css">
</head>
<body>

<header>
    <h1>Статистика</h1>
</header>

<div class="container">
    <h2>Статистика достопримечательностей</h2>
    <table>
        <tbody>
            <c:forEach var="stat" items="${statistics}">
                <tr>
                    <td>${stat[0]}</td>
                    <td>${stat[1]}</td>
                    <td>${stat[2]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body></html>
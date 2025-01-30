<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Достопримечательности Воронежа</title>
    <link rel="stylesheet" href="./CSS/allLandmarkStyle.css">
    <script src="./js/DropMenu.js"></script>
</head>
<body>
    <% String name = (String) session.getAttribute("login"); %>
    <div class="user-menu">
        <div class="user-name" onclick="toggleDropdown()"><%=name%> ▼</div>
        <div class="dropdown-content" id="dropdownMenu">
            <a href="${pageContext.request.contextPath}/favorites">Избранное</a>
            <a href="${pageContext.request.contextPath}/userSettings.jsp">Настройки профиля</a>
            <a href="${pageContext.request.contextPath}/statistics">Статистика</a>
            <a href="${pageContext.request.contextPath}/loginServlet">Выход</a>
        </div>
    </div>

    <header>
        <h1>Достопримечательности Воронежа</h1>
    </header>

    <form action="${pageContext.request.contextPath}/allLandmark" method="get" class="search-form">
        <input type="text" name="search" placeholder="Поиск по названию" value="${param.search}">
        <button type="submit">Поиск</button>
    </form>

    <div class="category-buttons">
        <form action="/allLandmark" method="get">
            <button type="submit" name="category" value="NOT NULL">Все</button>
            <button type="submit" name="category" value="1">Памятники</button>
            <button type="submit" name="category" value="2">Парки</button>
            <button type="submit" name="category" value="3">Соборы</button>
            <button type="submit" name="category" value="4">Прочее</button>
        </form>
    </div>

    <div class="container--parent">
        <div class="container">
            <c:if test="${not empty landmarkEntityList}">
                <c:forEach var="landmark" items="${landmarkEntityList}">
                    <a href="/allLandmark/${landmark.name}" class="card">
                        <img src="${photoEntityMap[landmark.id].imageURL}" alt="${landmark.name}">
                        <div class="card-title">${landmark.name}</div>
                    </a>
                </c:forEach>
            </c:if>
            <c:if test="${empty landmarkEntityList}">
                <div>Достопримечательности не найдены.</div>
            </c:if>
        </div>
    </div>


    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}&search=${param.search}">« Предыдущая</a>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="?page=${i}&search=${param.search}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a href="?page=${currentPage + 1}&search=${param.search}">Следующая »</a>
        </c:if>
    </div>
</body>
</html>
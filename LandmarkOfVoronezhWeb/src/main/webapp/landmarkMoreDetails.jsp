<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="landmarkOfVoronezhWeb.database.entity.LandmarkEntity" %>
<%@ page import="landmarkOfVoronezhWeb.database.entity.LocationEntity" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${landmarkName}</title>
    <style>
        <%@include file="./CSS/landmarkMoreDetailStyle.css"%>
    </style>
</head>
<body>

    <% LandmarkEntity landmarkEntity = (LandmarkEntity) request.getAttribute("landmarkEntity"); %>
    <% LocationEntity locationEntity = (LocationEntity) request.getAttribute("locationEntity"); %>


    <div class="header">
        <header>
            <h1>${landmarkName}</h1>
        </header>

        <div class="favorite">
            <form action="/allLandmark/${landmarkName}" method="post">
                <input type="hidden" name="landmarkId" value="${landmarkEntity.id}">
                <button class="button--input" type="submit">${textButtonFavorites}</button>
            </form>
        </div>
    </div>

    <div class="container">
        <img src=${mainPhoto.imageURL} alt="mainPhoto" class="landmark-image">

        <div class="section">
            <div class="section-title">Описание</div>
            <div class="section-content">
                <p><%= landmarkEntity.getDescription() %></p>
            </div>
        </div>

        <div class="section">
            <div class="section-title">История</div>
            <div class="section-content">
                <p><%= landmarkEntity.getHistory() %></p>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Местоположение</div>
            <div class="section-content">
                <c:if test="${not empty locationEntity}">
                    <div><%= locationEntity.getStreetName() + ", " +locationEntity.getHome() %></div>

                    <c:if test="${locationEntity.getCoordinates() != null}">
                        <div><%=locationEntity.getCoordinates()%></div>
                    </c:if>
                </c:if>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Галерея</div>
            <div class="gallery">
                <c:forEach var="photo" items="${photoEntityList}">
                    <img src="${photo.imageURL}" alt="photoInГалерея">
                </c:forEach>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Вырезки из новостей</div>
            <div class="news-snippet">
                <c:forEach var="historicalClipping" items="${historyEntityList}">
                    <a href="/news/${historicalClipping.nameClipping}" class="card">${historicalClipping.nameClipping}</a>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>


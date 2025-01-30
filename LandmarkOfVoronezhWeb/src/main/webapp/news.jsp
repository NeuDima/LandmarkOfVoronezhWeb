<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="landmarkOfVoronezhWeb.database.entity.HistoryEntity" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${newsName}</title>
    <style>
        <%@include file="/CSS/landmarkMoreDetailStyle.css"%>
    </style>
</head>
<body>

    <% HistoryEntity historyEntity = (HistoryEntity) request.getAttribute("historyEntity"); %>
    <%  %>

    <div class="header">
        <header>
            <h1>${newsName}</h1>
        </header>
    </div>

    <div class="container">
        <div class="section">
            <div class="section-title">Описание</div>
            <div class="section-content">
                <p><%= historyEntity.getDescription() %></p>
            </div>
        </div>


        <div class="section">
            <div class="section-title">Источник</div>
            <div class="section-content">
                <div><%= historyEntity.getSource() %></div>
            </div>
        </div>


        <c:if test="${historyEntity.getDate() != null}">
            <div class="section">
                <div class="section-title">Дата публикации</div>
                <div class="section-content">
                    <% Calendar calendar = historyEntity.getDate(); %>
                    <div><%= calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) %></div>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>

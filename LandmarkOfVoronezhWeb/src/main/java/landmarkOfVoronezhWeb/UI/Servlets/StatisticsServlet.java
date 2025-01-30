package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;
import landmarkOfVoronezhWeb.service.StatisticsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    StatisticsService statisticsService = new StatisticsService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String countLandmarkStr = "Общее количество достопримечательностей на сайте";
        int countLandmark = statisticsService.getLandmarkSize();

        String landmarkFavoritesStr = "Самая популярная достопримечательность в избранных";
        StatisticsEntity landmarkFavorites = statisticsService.getLandmarkMoreFavorites();

        String landmarkPhotosStr = "Самая фотогеничная достопримечательность";
        StatisticsEntity landmarkPhotos = statisticsService.getLandmarkMorePhotos();

        String streetLandmarkStr = "Улица с наибоьшим количеством достопримечательностей";
        StatisticsEntity streetLandmark = statisticsService.getStreetMoreLandmark();

        String landmarkVisitsStr = "Самая популярная достопримечательность на сайте";
        StatisticsEntity landmarkVisits = statisticsService.getLandmarkMoreVisits();

        String landmarkNewsStr = "Самая часто встречаемая достопримечательность в новостях";
        StatisticsEntity landmarkNews = statisticsService.getLandmarkMoreNews();

        List<String[]> statistics = new ArrayList<>();
        statistics.add(new String[]{countLandmarkStr, "", String.valueOf(countLandmark)});
        statistics.add(new String[]{landmarkFavoritesStr, landmarkFavorites.getName(), String.valueOf(landmarkFavorites.getCount())});
        statistics.add(new String[]{landmarkPhotosStr, landmarkPhotos.getName(), String.valueOf(landmarkPhotos.getCount())});
        statistics.add(new String[]{streetLandmarkStr, streetLandmark.getName(), String.valueOf(streetLandmark.getCount())});
        statistics.add(new String[]{landmarkVisitsStr, landmarkVisits.getName(), String.valueOf(landmarkVisits.getCount())});
        statistics.add(new String[]{landmarkNewsStr, landmarkNews.getName(), String.valueOf(landmarkNews.getCount())});

        request.setAttribute("statistics", statistics);
        request.getRequestDispatcher("/statistics.jsp").forward(request, response);
    }
}
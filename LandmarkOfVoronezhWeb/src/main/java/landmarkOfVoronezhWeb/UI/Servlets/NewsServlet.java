package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import landmarkOfVoronezhWeb.database.entity.HistoryEntity;
import landmarkOfVoronezhWeb.service.LandmarkService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/news/*")
public class NewsServlet extends HttpServlet {
    private final LandmarkService landmarkService = new LandmarkService();
    Logger LOGGER = Logger.getLogger(NewsServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String newsName = pathInfo != null ? pathInfo.substring(1) : null;

        HistoryEntity historyEntity = landmarkService.getHistoryByName(newsName);

        if (historyEntity == null) {
            historyEntity = landmarkService.getHistoryByName(newsName + "?");
        }

        request.setAttribute("newsName", newsName);
        request.setAttribute("historyEntity", historyEntity);
        request.getRequestDispatcher("/news.jsp").forward(request, response);
    }
}

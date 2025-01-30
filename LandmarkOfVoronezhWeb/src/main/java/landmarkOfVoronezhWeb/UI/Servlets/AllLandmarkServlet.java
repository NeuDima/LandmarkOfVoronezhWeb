package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;
import landmarkOfVoronezhWeb.service.LandmarkService;

import java.util.Map;

import java.io.IOException;
import java.util.List;

@WebServlet("/allLandmark")
public class AllLandmarkServlet extends HttpServlet {
    private final int ITEMS_PER_PAGE = 4;

    LandmarkService landmarkService = new LandmarkService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        String searchParam = request.getParameter("search");
        String search;
        String category;

        HttpSession session = request.getSession();
        if (searchParam != null) {
            search = searchParam;
            category = (String) session.getAttribute("category");
        } else {
            search = "";
            category = request.getParameter("category");
            if (category == null) {
                category = "NOT NULL";
            }
        }

        session.setAttribute("category", category);

        List<LandmarkEntity> landmarkEntityList = landmarkService.getLandmarksForPage(search, category, currentPage, ITEMS_PER_PAGE);
        Map<Integer, PhotoEntity> photoEntityMap = landmarkService.getFirstPhotoByLandmarks(landmarkEntityList);

        int totalPages = landmarkService.countPage(search, ITEMS_PER_PAGE);

        List<Integer> allCategory = landmarkService.getAllCategory();

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("allCategory", allCategory);

        request.setAttribute("photoEntityMap", photoEntityMap);
        request.setAttribute("landmarkEntityList", landmarkEntityList);
        request.setAttribute("search", search);
        request.getRequestDispatcher("/allLandmark.jsp").forward(request, response);
    }
}

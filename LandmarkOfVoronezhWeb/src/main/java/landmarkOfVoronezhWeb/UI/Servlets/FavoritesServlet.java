package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;
import landmarkOfVoronezhWeb.service.FavoritesService;
import landmarkOfVoronezhWeb.service.LandmarkService;

import java.util.Map;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {
    LandmarkService landmarkService = new LandmarkService();
    FavoritesService favoritesService = new FavoritesService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute("idUser");
        List<LandmarkEntity> landmarkEntityList = favoritesService.getFavorites(idUser);
        Map<Integer, PhotoEntity> photoEntityMap = landmarkService.getFirstPhotoByLandmarks(landmarkEntityList);

        request.setAttribute("photoEntityMap", photoEntityMap);
        request.setAttribute("landmarkEntityList", landmarkEntityList);
        request.getRequestDispatcher("/favorites.jsp").forward(request, response);
    }
}
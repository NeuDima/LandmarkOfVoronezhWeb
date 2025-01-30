package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.service.FavoritesService;
import landmarkOfVoronezhWeb.service.LandmarkService;

import java.io.IOException;
import java.util.logging.Logger;


@WebServlet("/buttonFavorites")
public class ButtonFavoritesServlet extends HttpServlet {
    private final LandmarkService landmarkService = new LandmarkService();
    FavoritesService favoritesService = new FavoritesService();
    private static final Logger logger = Logger.getLogger(ButtonFavoritesServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String attractionIdParam = request.getParameter("landmarkId");
        int landmarkId = Integer.parseInt(attractionIdParam);

        int idUser = (int) session.getAttribute("idUser");
        LandmarkEntity landmarkEntity = landmarkService.getLandmarkById(landmarkId);

        if ((boolean) request.getAttribute("isFavorites")) {
            logger.info("Попытка удаления");
            favoritesService.removeFavorites(landmarkEntity, idUser);
        } else {
            logger.info("Попытка добавления");
            favoritesService.addFavorites(landmarkEntity, idUser);
        }
        request.setAttribute("landmarkEntity", landmarkEntity);
        request.getRequestDispatcher("/allLandmark").forward(request, response);
    }
}
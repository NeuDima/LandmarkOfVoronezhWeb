package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import landmarkOfVoronezhWeb.database.entity.HistoryEntity;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.LocationEntity;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;
import landmarkOfVoronezhWeb.service.FavoritesService;
import landmarkOfVoronezhWeb.service.LandmarkService;

import java.io.IOException;
import java.util.List;

@WebServlet("/allLandmark/*")
public class LandmarkMoreDetailsServlet extends HttpServlet {
    private final LandmarkService landmarkService = new LandmarkService();
    FavoritesService favoritesService = new FavoritesService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int idUser = (int) session.getAttribute("idUser");
        LandmarkEntity landmarkEntity = (LandmarkEntity) session.getAttribute("landmarkEntity");

        if ((boolean) session.getAttribute("isFavorites")) {
            favoritesService.removeFavorites(landmarkEntity, idUser);
        } else {
            favoritesService.addFavorites(landmarkEntity, idUser);
        }
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String pathInfo = request.getPathInfo();
        String landmarkName = pathInfo != null ? pathInfo.substring(1) : null;

        LandmarkEntity landmarkEntity = landmarkService.getLandmarkByName(landmarkName);

        int idLandmark = landmarkEntity.getId();
        if (session.getAttribute("isFavorites") == null) landmarkService.addVisits(idLandmark);

        List<PhotoEntity> photoEntityList = landmarkService.getListPhoto(idLandmark);
        PhotoEntity mainPhoto = photoEntityList.get(0);

        LocationEntity locationEntity = landmarkService.getLocationById(idLandmark);

        List<HistoryEntity> historyEntityList = landmarkService.getHistoriesByIdLandmark(idLandmark);

        int idUser = (int) session.getAttribute("idUser");
        boolean isFavorites = favoritesService.checkIsFavorites(landmarkEntity, idUser);
        String textButtonFavorites = (isFavorites) ? "Удалить из избанного" : "Добавить в избранное";

        request.setAttribute("landmarkName", landmarkName);
        request.setAttribute("landmarkEntity", landmarkEntity);
        request.setAttribute("photoEntityList", photoEntityList);
        request.setAttribute("mainPhoto", mainPhoto);
        request.setAttribute("locationEntity", locationEntity);
        request.setAttribute("historyEntityList", historyEntityList);
        request.setAttribute("textButtonFavorites", textButtonFavorites);
        session.setAttribute("isFavorites", isFavorites);
        session.setAttribute("landmarkEntity", landmarkEntity);
        request.getRequestDispatcher("/landmarkMoreDetails.jsp").forward(request, response);
    }
}
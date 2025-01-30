package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ILandmarkRepository {
    List<LandmarkEntity> getAllLandmark();

    LandmarkEntity getLandmarkById(int idLandmark) throws SQLException;

    ArrayList<LandmarkEntity> getLandmarksByIds(ArrayList<Integer> idLandmark);

    ArrayList<LandmarkEntity> getLandmarksForPageBySearch(String search, String category, int currentPage, int countOnPage);

    int countPage(String search, int countOnPage);

    List<Integer> getAllCategories();

    boolean addVisits(int idLandmark);

    int getSize();

    LandmarkEntity getLandmarkByName(String name);

    StatisticsEntity getLandmarkMoreFavorites();

    StatisticsEntity getLandmarkMorePhotos();

    StatisticsEntity getLandmarkMoreVisits();

    StatisticsEntity getLandmarkMoreNews();
}

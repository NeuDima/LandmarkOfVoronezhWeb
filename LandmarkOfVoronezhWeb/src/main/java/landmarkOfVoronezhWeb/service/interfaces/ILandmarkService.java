package landmarkOfVoronezhWeb.service.interfaces;

import landmarkOfVoronezhWeb.database.entity.HistoryEntity;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.LocationEntity;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ILandmarkService {

    List<PhotoEntity> getListPhoto(int idLandmark);

    List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark);

    LandmarkEntity getLandmarkById(int idLandmark) throws SQLException;

    LocationEntity getLocationById(int idLandmark);

    List<LandmarkEntity> getLandmarksForPage(String search, String category, int currentPage, int countOnPage);

    List<Integer> getAllCategory();

    Map<Integer, PhotoEntity> getFirstPhotoByLandmarks(List<LandmarkEntity> landmarkEntityList);

    int getLandmarkSize();

    LandmarkEntity getLandmarkByName(String name);

    HistoryEntity getHistoryByName(String name);

    int countPage(String search, int countOnPage);

    boolean addVisits(int idLandmark);
}

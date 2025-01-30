package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.PhotoEntity;

import java.util.List;
import java.util.Map;

public interface IPhotoRepository {
    List<PhotoEntity> getPhotosByIdLandmark(int idLandmark);

    Map<Integer, PhotoEntity> getFirstPhotoByIdsLandmarks(List<Integer> idLandmarks);
}

package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.UserLandmarkEntity;

import java.util.List;

public interface IUserLandmarkRepository {
    List<LandmarkEntity> getLandmarksByIdUser(int idUser);

    void addUserLandmark(UserLandmarkEntity userLandmark);

    void deleteUserLandmark(UserLandmarkEntity userLandmark);
}

package landmarkOfVoronezhWeb.service;

import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.UserLandmarkEntity;
import landmarkOfVoronezhWeb.database.repository.UserLandmarkRepository;
import landmarkOfVoronezhWeb.service.interfaces.IFavoritesService;

import java.util.ArrayList;

public class FavoritesService implements IFavoritesService {
    private final UserLandmarkRepository userLandmarkRepository = UserLandmarkRepository.getInstance();

    @Override
    public ArrayList<LandmarkEntity> getFavorites(int idUser) {
        return userLandmarkRepository.getLandmarksByIdUser(idUser);
    }

    @Override
    public boolean checkIsFavorites(LandmarkEntity landmark, int idUser) {
        return userLandmarkRepository.getUserLandmarkByIdUserAndIdLandmark(idUser, landmark.getId()) != null;
    }

    @Override
    public void addFavorites(LandmarkEntity landmark, int idUser) {
        if (checkIsFavorites(landmark, idUser)) return;
        userLandmarkRepository.addUserLandmark(new UserLandmarkEntity(
                idUser,
                landmark.getId())
        );
    }

    @Override
    public void removeFavorites(LandmarkEntity landmark, int idUser) {
        if (!checkIsFavorites(landmark, idUser)) return;
        userLandmarkRepository.deleteUserLandmark(new UserLandmarkEntity(idUser, landmark.getId()));
    }
}

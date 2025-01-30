package landmarkOfVoronezhWeb.service.interfaces;

import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;

import java.util.List;

public interface IFavoritesService {
    List<LandmarkEntity> getFavorites(int idUser);

    boolean checkIsFavorites(LandmarkEntity landmark, int idUser);

    void addFavorites(LandmarkEntity landmark, int idUser);

    void removeFavorites(LandmarkEntity landmark, int idUser);
}

package landmarkOfVoronezhWeb.service.interfaces;

import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;

public interface IStatisticsService {
    StatisticsEntity getLandmarkMoreFavorites();

    StatisticsEntity getLandmarkMorePhotos();

    StatisticsEntity getLandmarkMoreVisits();

    StatisticsEntity getLandmarkMoreNews();

    StatisticsEntity getStreetMoreLandmark();

    int getLandmarkSize();
}

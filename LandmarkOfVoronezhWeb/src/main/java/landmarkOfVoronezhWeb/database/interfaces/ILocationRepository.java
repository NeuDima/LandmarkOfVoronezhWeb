package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.LocationEntity;
import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;

public interface ILocationRepository {
    LocationEntity getLandmarkById(int idLandmark);

    StatisticsEntity getStreetMoreLandmark();
}

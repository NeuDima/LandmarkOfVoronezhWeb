package landmarkOfVoronezhWeb.service;

import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;
import landmarkOfVoronezhWeb.database.repository.LandmarkRepository;
import landmarkOfVoronezhWeb.database.repository.LocationRepository;
import landmarkOfVoronezhWeb.service.interfaces.IStatisticsService;

public class StatisticsService implements IStatisticsService {
    private final LandmarkRepository landmarkRepository = LandmarkRepository.getInstance();
    private final LocationRepository locationRepository = LocationRepository.getInstance();
    private final LandmarkService landmarkService = new LandmarkService();

    @Override
    public StatisticsEntity getLandmarkMoreFavorites() {
        return landmarkRepository.getLandmarkMoreFavorites();
    }

    @Override
    public StatisticsEntity getLandmarkMorePhotos() {
        return landmarkRepository.getLandmarkMorePhotos();
    }

    @Override
    public StatisticsEntity getLandmarkMoreVisits() {
        return landmarkRepository.getLandmarkMoreVisits();
    }

    @Override
    public StatisticsEntity getLandmarkMoreNews() {
        return landmarkRepository.getLandmarkMoreNews();
    }

    @Override
    public StatisticsEntity getStreetMoreLandmark() {
        return locationRepository.getStreetMoreLandmark();
    }

    @Override
    public int getLandmarkSize() {
        return landmarkService.getLandmarkSize();
    }
}

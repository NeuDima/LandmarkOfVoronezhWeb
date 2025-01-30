package landmarkOfVoronezhWeb.service;

import landmarkOfVoronezhWeb.database.entity.HistoryEntity;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.LocationEntity;
import landmarkOfVoronezhWeb.database.entity.PhotoEntity;
import landmarkOfVoronezhWeb.database.repository.HistoryRepository;
import landmarkOfVoronezhWeb.database.repository.LandmarkRepository;
import landmarkOfVoronezhWeb.database.repository.LocationRepository;
import landmarkOfVoronezhWeb.database.repository.PhotoRepository;
import landmarkOfVoronezhWeb.service.interfaces.ILandmarkService;

import java.util.*;

public class LandmarkService implements ILandmarkService {

    private final HistoryRepository historyRepository = HistoryRepository.getInstance();
    private final LandmarkRepository landmarkRepository = LandmarkRepository.getInstance();
    private final LocationRepository locationRepository = LocationRepository.getInstance();
    private final PhotoRepository photoRepository = PhotoRepository.getInstance();

    @Override
    public List<PhotoEntity> getListPhoto(int idLandmark) {
        return photoRepository.getPhotosByIdLandmark(idLandmark);
    }

    @Override
    public List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark) {
        return historyRepository.getHistoriesByIdLandmark(idLandmark);
    }

    @Override
    public LandmarkEntity getLandmarkById(int idLandmark) {
        return landmarkRepository.getLandmarkById(idLandmark);
    }

    @Override
    public LocationEntity getLocationById(int idLandmark) {
        return locationRepository.getLandmarkById(idLandmark);
    }

    @Override
    public List<LandmarkEntity> getLandmarksForPage(String search, String category, int currentPage, int countOnPage) {
        return landmarkRepository.getLandmarksForPageBySearch(search, category, currentPage, countOnPage);
    }

    @Override
    public List<Integer> getAllCategory() {
        return landmarkRepository.getAllCategories();
    }

    @Override
    public Map<Integer, PhotoEntity> getFirstPhotoByLandmarks(List<LandmarkEntity> landmarkEntityList) {
        List<Integer> landmarkIdList = new ArrayList<>();
        for (LandmarkEntity landmarkEntity : landmarkEntityList) {
            landmarkIdList.add(landmarkEntity.getId());
        }
        return photoRepository.getFirstPhotoByIdsLandmarks(landmarkIdList);
    }

    @Override
    public int getLandmarkSize() {
        return landmarkRepository.getSize();
    }

    @Override
    public LandmarkEntity getLandmarkByName(String name) {
        return landmarkRepository.getLandmarkByName(name);
    }

    @Override
    public HistoryEntity getHistoryByName(String name) {
        return historyRepository.getHistoryByName(name);
    }

    @Override
    public int countPage(String search, int countOnPage) {
        return landmarkRepository.countPage(search, countOnPage);
    }

    @Override
    public boolean addVisits(int idLandmark) {
        return landmarkRepository.addVisits(idLandmark);
    }
}

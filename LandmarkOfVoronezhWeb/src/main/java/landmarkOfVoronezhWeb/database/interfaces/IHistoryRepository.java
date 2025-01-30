package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.HistoryEntity;

import java.util.List;

public interface IHistoryRepository {
    List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark);

    HistoryEntity getHistoryByName(String name);
}

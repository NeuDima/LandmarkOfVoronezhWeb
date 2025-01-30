package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.HistoryEntity;
import landmarkOfVoronezhWeb.database.mapper.HistoryMapper;
import landmarkOfVoronezhWeb.database.interfaces.IHistoryRepository;

import java.sql.*;
import java.util.*;

public class HistoryRepository implements IHistoryRepository {
    private static HistoryRepository instance = null;
    private HistoryRepository() {}
    public static HistoryRepository getInstance() {
        if (instance == null) {
            instance = new HistoryRepository();
        }
        return instance;
    }

    HistoryMapper historyMapper = new HistoryMapper();

    @Override
    public List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark) {
        ArrayList<HistoryEntity> list = new ArrayList<>();
        String query = "SELECT * FROM historical_clipping WHERE id_landmark = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idLandmark);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HistoryEntity historyEntity = historyMapper.mapRow(resultSet);
                list.add(historyEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public HistoryEntity getHistoryByName(String name) {
        String query = "SELECT * FROM historical_clipping WHERE name_clipping = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return historyMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}



package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.LocationEntity;
import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;
import landmarkOfVoronezhWeb.database.mapper.LocationMapper;
import landmarkOfVoronezhWeb.database.interfaces.ILocationRepository;
import landmarkOfVoronezhWeb.database.mapper.StatisticsMapper;

import java.sql.*;

public class LocationRepository implements ILocationRepository {
    private static LocationRepository instance = null;
    private LocationRepository() {}
    public static LocationRepository getInstance() {
        if (instance == null) {
            instance = new LocationRepository();
        }
        return instance;
    }

    LocationMapper locationMapper = new LocationMapper();
    StatisticsMapper statisticsMapper = new StatisticsMapper();

    @Override
    public LocationEntity getLandmarkById(int idLandmark) {
        String query = "SELECT * FROM location_landmark WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idLandmark);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return locationMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public StatisticsEntity getStreetMoreLandmark() {
        String sql =
                """
                        SELECT ll.street_name, COUNT(l.id) AS landmark_count
                        \tFROM location_landmark ll
                        \tJOIN landmark l ON ll.id = l.id
                        \tGROUP BY ll.street_name
                        \tORDER BY landmark_count DESC
                        \tLIMIT 1;""";

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            assert statement != null;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return statisticsMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

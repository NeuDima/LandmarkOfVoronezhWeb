package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.StatisticsEntity;
import landmarkOfVoronezhWeb.database.mapper.LandmarkMapper;
import landmarkOfVoronezhWeb.database.interfaces.ILandmarkRepository;
import landmarkOfVoronezhWeb.database.mapper.StatisticsMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LandmarkRepository implements ILandmarkRepository {
    private static LandmarkRepository instance = null;

    private LandmarkRepository() {
    }

    public static LandmarkRepository getInstance() {
        if (instance == null) {
            instance = new LandmarkRepository();
        }
        return instance;
    }

    LandmarkMapper landmarkMapper = new LandmarkMapper();
    StatisticsMapper statisticsMapper = new StatisticsMapper();

    @Override
    public List<LandmarkEntity> getAllLandmark() {
        ArrayList<LandmarkEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM landmark";

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            assert statement != null;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                LandmarkEntity landmarkEntity = landmarkMapper.mapRow(resultSet);
                list.add(landmarkEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public LandmarkEntity getLandmarkById(int idLandmark) {
        String query = "SELECT * FROM landmark WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idLandmark);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return landmarkMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<LandmarkEntity> getLandmarksByIds(ArrayList<Integer> idLandmark) {
        ArrayList<LandmarkEntity> list = new ArrayList<>();
        String query = "SELECT * FROM landmark WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int idLandmarks : idLandmark) {
                assert preparedStatement != null;
                preparedStatement.setInt(1, idLandmarks);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    LandmarkEntity landmarkEntity = landmarkMapper.mapRow(resultSet);
                    list.add(landmarkEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public ArrayList<LandmarkEntity> getLandmarksForPageBySearch(String search, String category, int currentPage, int countOnPage) {
        if (category.equals("NOT NULL")) return getLandmarksForPageBySearch(search, currentPage, countOnPage);

        ArrayList<LandmarkEntity> list = new ArrayList<>();
        String query = "SELECT * FROM landmark WHERE LOWER(name) LIKE LOWER(?) AND category = ? ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, Integer.parseInt(category));
            preparedStatement.setInt(3, countOnPage);
            preparedStatement.setInt(4, (currentPage - 1) * countOnPage);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LandmarkEntity landmarkEntity = landmarkMapper.mapRow(resultSet);
                list.add(landmarkEntity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private ArrayList<LandmarkEntity> getLandmarksForPageBySearch(String search, int currentPage, int countOnPage) {
        ArrayList<LandmarkEntity> list = new ArrayList<>();
        String query = "SELECT * FROM landmark WHERE LOWER(name) LIKE LOWER(?) ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, countOnPage);
            preparedStatement.setInt(3, (currentPage - 1) * countOnPage);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LandmarkEntity landmarkEntity = landmarkMapper.mapRow(resultSet);
                list.add(landmarkEntity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public int countPage(String search, int countOnPage) {
        String query = "SELECT COUNT(*) FROM landmark WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return (int) Math.ceil((double) resultSet.getInt(1) / countOnPage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public LandmarkEntity getLandmarkByName(String name) {
        String query = "SELECT * FROM landmark WHERE name = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return landmarkMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Integer> getAllCategories() {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = """
                SELECT DISTINCT category
                FROM landmark
                ORDER BY category;""";

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            assert statement != null;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean addVisits(int idLandmark) {
        String query = """
                UPDATE landmark
                SET count_visits = count_visits + 1
                WHERE id = ?;""";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idLandmark);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Запросы для статистики
    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) FROM landmark";

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            assert statement != null;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public StatisticsEntity getLandmarkMoreFavorites() {
        String sql =
                """
                        SELECT lm.name, COUNT(ul.landmark_id) AS favorite_count
                        \tFROM landmark lm
                        \tJOIN user_landmark ul ON lm.id = ul.landmark_id
                        \tGROUP BY lm.id, lm.name
                        \tORDER BY favorite_count DESC\s
                        \tLIMIT 1;""";

        return statistics(sql);
    }

    @Override
    public StatisticsEntity getLandmarkMorePhotos() {
        String sql =
                """
                        SELECT lm.name, COUNT(p.id) AS photo_count
                        FROM landmark lm
                        LEFT JOIN photo p ON lm.id = p.id_landmark
                        GROUP BY lm.id, lm.name
                        ORDER BY photo_count DESC
                        LIMIT 1;""";

        return statistics(sql);
    }

    @Override
    public StatisticsEntity getLandmarkMoreVisits() {
        String sql =
                """
                        SELECT lm.name, lm.count_visits
                        \tFROM landmark lm
                        \tORDER BY lm.count_visits DESC
                        \tLIMIT 1;""";

        return statistics(sql);
    }

    @Override
    public StatisticsEntity getLandmarkMoreNews() {
        String sql =
                """
                        SELECT lm.name, COUNT(hc.id) AS news_count
                        \tFROM landmark lm
                        \tLEFT JOIN historical_clipping hc ON lm.id = hc.id_landmark
                        \tGROUP BY lm.id, lm.name
                        \tORDER BY news_count DESC
                        \tLIMIT 1;""";

        return statistics(sql);
    }

    private StatisticsEntity statistics(String sql) {
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
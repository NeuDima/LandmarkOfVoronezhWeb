package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.LandmarkEntity;
import landmarkOfVoronezhWeb.database.entity.UserLandmarkEntity;
import landmarkOfVoronezhWeb.database.mapper.UserLandmarkMapper;
import landmarkOfVoronezhWeb.database.interfaces.IUserLandmarkRepository;

import java.sql.*;
import java.util.ArrayList;


public class UserLandmarkRepository implements IUserLandmarkRepository {
    private static UserLandmarkRepository instance = null;
    private UserLandmarkRepository() {}
    public static UserLandmarkRepository getInstance() {
        if (instance == null) {
            instance = new UserLandmarkRepository();
        }
        return instance;
    }

    LandmarkRepository landmarkRepository = LandmarkRepository.getInstance();
    UserLandmarkMapper userLandmarkMapper = new UserLandmarkMapper();

    @Override
    public void addUserLandmark(UserLandmarkEntity userLandmark) {
        String query = "INSERT INTO public.user_landmark (user_id, landmark_id) values (?, ?)";
        changeUserLandmark(query, userLandmark);
    }

    @Override
    public void deleteUserLandmark(UserLandmarkEntity userLandmark) {
        String query = "DELETE FROM public.user_landmark WHERE user_id = ? AND landmark_id = ?";
        changeUserLandmark(query, userLandmark);
    }

    private void changeUserLandmark(String query, UserLandmarkEntity userLandmark) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, userLandmark.getIdUser());
            preparedStatement.setInt(2, userLandmark.getIdLandmark());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<LandmarkEntity> getLandmarksByIdUser(int idUser) {
        ArrayList<Integer> list = new ArrayList<>();
        String query = "SELECT * FROM user_landmark WHERE user_id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserLandmarkEntity userLandmarkEntity = userLandmarkMapper.mapRow(resultSet);
                list.add(userLandmarkEntity.getIdLandmark());
            }

            return landmarkRepository.getLandmarksByIds(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserLandmarkEntity getUserLandmarkByIdUserAndIdLandmark(int idUser, int idLandmark) {
        UserLandmarkMapper userLandmarkMapper = new UserLandmarkMapper();
        String query = "SELECT * FROM user_landmark WHERE user_id = ? AND landmark_id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idLandmark);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return userLandmarkMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

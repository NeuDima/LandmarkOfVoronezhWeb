package landmarkOfVoronezhWeb.database.repository;

import landmarkOfVoronezhWeb.database.connection.ConnectionManager;
import landmarkOfVoronezhWeb.database.entity.UserEntity;
import landmarkOfVoronezhWeb.database.mapper.UserMapper;
import landmarkOfVoronezhWeb.database.interfaces.IUserRepository;

import java.sql.*;

public class UserRepository implements IUserRepository {
    private static UserRepository instance = null;
    private UserRepository() {}
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    UserMapper userMapper = new UserMapper();

    @Override
    public void addUser(UserEntity user) {
        String query = "INSERT INTO user_date (login, password) values (?, ?)";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserEntity userOld, UserEntity userNew) {
        String query = "UPDATE user_date SET login = ?, password = ? WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setString(1, userNew.getLogin());
            preparedStatement.setString(2, userNew.getPassword());
            preparedStatement.setInt(3, userOld.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(UserEntity user) {
        String query = "DELETE FROM user_date WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        String query = "SELECT * FROM user_date WHERE login = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return userMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserEntity getUserById(int id) {
        String query = "SELECT * FROM user_date WHERE id = ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            assert preparedStatement != null;
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return userMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

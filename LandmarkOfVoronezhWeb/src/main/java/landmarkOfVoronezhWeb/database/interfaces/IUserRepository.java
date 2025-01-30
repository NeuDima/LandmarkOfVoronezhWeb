package landmarkOfVoronezhWeb.database.interfaces;

import landmarkOfVoronezhWeb.database.entity.UserEntity;

public interface IUserRepository {
    UserEntity getUserByLogin(String login);

    UserEntity getUserById(int id);

    void addUser(UserEntity user);

    void updateUser(UserEntity userOld, UserEntity userNew);

    void deleteUser(UserEntity user);
}

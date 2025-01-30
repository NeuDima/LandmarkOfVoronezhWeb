package landmarkOfVoronezhWeb.service;

import landmarkOfVoronezhWeb.database.entity.UserEntity;
import landmarkOfVoronezhWeb.database.repository.UserRepository;
import landmarkOfVoronezhWeb.service.interfaces.IUsersService;

public class UsersService implements IUsersService {
    private final UserRepository usersRepository = UserRepository.getInstance();

    @Override
    public boolean checkUserByLogin(String login, String password) {
        UserEntity userEntity = usersRepository.getUserByLogin(login);
        if (userEntity != null) {
            return userEntity.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public boolean isNewUser(String login, String password) {
        if (usersRepository.getUserByLogin(login) == null) {
            usersRepository.addUser(new UserEntity(login, password));
            return true;
        }
        return false;
    }

    @Override
    public int getIdByLogin(String login) {
        return usersRepository.getUserByLogin(login).getId();
    }

    @Override
    public boolean checkUserLoginIsUse(String login) {
        UserEntity userEntity = usersRepository.getUserByLogin(login);
        return userEntity != null;
    }
}

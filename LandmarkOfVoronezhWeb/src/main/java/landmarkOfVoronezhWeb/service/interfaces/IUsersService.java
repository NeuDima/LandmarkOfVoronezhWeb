package landmarkOfVoronezhWeb.service.interfaces;

public interface IUsersService {
    boolean checkUserByLogin(String login, String password);

    boolean isNewUser(String login, String password);

    int getIdByLogin(String login);

    boolean checkUserLoginIsUse(String login);
}

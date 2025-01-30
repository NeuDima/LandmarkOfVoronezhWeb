package landmarkOfVoronezhWeb.service.interfaces;

public interface IUserSettingsService {

    boolean updateLoginUser(String loginNew);

    void updatePasswordUser(String passwordNew);

    void deleteUser();
}

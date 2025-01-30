package landmarkOfVoronezhWeb.UI.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import landmarkOfVoronezhWeb.service.UserSettingsService;
import landmarkOfVoronezhWeb.service.UsersService;

import java.io.IOException;

@WebServlet("/changePassword")
public class UserSettingsChangePasswordServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = new UsersService();

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        int id = usersService.getIdByLogin(login);
        UserSettingsService userSettingsService = new UserSettingsService(id);

        String passwordOld = request.getParameter("passwordOld");
        String passwordNew = request.getParameter("passwordNew");
        String passwordNewRepeat = request.getParameter("passwordNewRepeat");

        if (usersService.checkUserByLogin(login, passwordOld)) {
            if (!passwordNew.equals(passwordNewRepeat)) {
                request.setAttribute("errorNewPassword", "Пароли не совпадают");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            } else {
                userSettingsService.updatePasswordUser(passwordNew);
                response.sendRedirect("allLandmark");
            }
        } else {
            request.setAttribute("errorNewPassword", "Ошибка в старом пароле");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }
}
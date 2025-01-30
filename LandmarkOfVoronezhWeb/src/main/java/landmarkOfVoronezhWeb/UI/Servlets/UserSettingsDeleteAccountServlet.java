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

@WebServlet("/deleteAccount")
public class UserSettingsDeleteAccountServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("deleteAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = new UsersService();

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        int id = usersService.getIdByLogin(login);
        UserSettingsService userSettingsService = new UserSettingsService(id);

        String password = request.getParameter("password");

        if (usersService.checkUserByLogin(login, password)) {
            userSettingsService.deleteUser();
            session.setAttribute("login", null);
            response.sendRedirect("loginServlet");
        } else {
            request.setAttribute("errorDeleteAccount", "Ошибка в пароле");
            request.getRequestDispatcher("deleteAccount").forward(request, response);
        }
    }
}
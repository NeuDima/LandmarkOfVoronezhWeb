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

@WebServlet("/changeLogin")
public class UserSettingsChangeLoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("changeLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = new UsersService();

        HttpSession session = request.getSession();
        String oldLogin = (String) session.getAttribute("login");

        int id = usersService.getIdByLogin(oldLogin);
        UserSettingsService userSettingsService = new UserSettingsService(id);

        String newLogin = request.getParameter("login");
        String password = request.getParameter("password");

        if (usersService.checkUserByLogin(oldLogin, password)) {
            if (usersService.checkUserLoginIsUse(newLogin)) {
                request.setAttribute("errorNewLogin", "Данный логин занят");
                request.getRequestDispatcher("changeLogin.jsp").forward(request, response);
            } else {
                userSettingsService.updateLoginUser(newLogin);
                session.setAttribute("login", newLogin);
                response.sendRedirect("allLandmark");
            }
        } else {
            request.setAttribute("errorNewLogin", "Ошибка в логине или пароле");
            request.getRequestDispatcher("changeLogin.jsp").forward(request, response);
        }
    }
}
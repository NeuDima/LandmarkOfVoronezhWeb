package landmarkOfVoronezhWeb.UI.Servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import landmarkOfVoronezhWeb.service.UsersService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null) {
            session.setAttribute("login", null);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = new UsersService();
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (usersService.checkUserByLogin(login, password)) {
            int idUser = usersService.getIdByLogin(login);
            session.setAttribute("idUser", idUser);
            session.setAttribute("login", login);
            response.sendRedirect("allLandmark");
        } else {
            request.setAttribute("errorLoginOrPassword", "Ошибка в логине или пароле");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
package landmarkOfVoronezhWeb.UI.Servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import landmarkOfVoronezhWeb.service.UsersService;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = new UsersService();
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");

        if (password.equals(passwordRepeat)) {
            if (usersService.isNewUser(login, password)) {
                int idUser = usersService.getIdByLogin(login);
                session.setAttribute("idUser", idUser);
                session.setAttribute("login", login);
                response.sendRedirect("allLandmark");
            } else {
                request.setAttribute("errorLoginIsAlreadyInUse", "Данный логин уже занят");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            if (usersService.checkUserLoginIsUse(login)) {
                request.setAttribute("errorLoginIsAlreadyInUse", "Данный логин уже занят");
            }
            request.setAttribute("errorNotEqualsPassword", "Пароли не совпадают");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
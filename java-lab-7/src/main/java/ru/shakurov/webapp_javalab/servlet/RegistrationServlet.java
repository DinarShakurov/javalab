package ru.shakurov.webapp_javalab.servlet;

import ru.shakurov.context.ApplicationContext;
import ru.shakurov.webapp_javalab.services.AuthService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/registration")
public class RegistrationServlet extends HttpServlet {
    private ApplicationContext applicationContext;
    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        this.authService = applicationContext.getComponent(AuthService.class, "AuthService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println(login+" " + password);
        try {
            authService.signUp(login, password);
        } catch (Exception e) {
            resp.sendRedirect("/main/registration" + "?status=incorrect");
            return;
        }
        resp.sendRedirect("/main" + "?status=successfully");
    }
}

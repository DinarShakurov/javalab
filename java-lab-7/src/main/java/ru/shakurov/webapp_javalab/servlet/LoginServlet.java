package ru.shakurov.webapp_javalab.servlet;

import ru.shakurov.context.ApplicationContext;
import ru.shakurov.webapp_javalab.dto.UserDto;
import ru.shakurov.webapp_javalab.services.AuthService;
import ru.shakurov.webapp_javalab.services.impl.AuthServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;
    private ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        authService = applicationContext.getComponent(AuthService.class, "AuthService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDto userDto;
        System.out.println(login +" " +password);
        try {
            userDto = authService.signIn(login, password);
        } catch (Exception e) {
            resp.sendRedirect("/main/login" + "?status=incorrect");
            return;
        }
        req.getSession().setAttribute("user", userDto);
        resp.sendRedirect("/goods");

    }
}

package com.epam.cdp.jee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Vladislav on 23.09.2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final static Logger log =  Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");

        if (password.isEmpty() || "wrong".equals(password)) {
            log.warning("Login failure, redirect to proper page");
            response.sendRedirect(request.getContextPath() + "/errors/unauthorised.html");
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", login);
            log.info("Login succeed, redirect to proper page");
            response.sendRedirect(request.getContextPath() + "/app.html");
        }
    }

}

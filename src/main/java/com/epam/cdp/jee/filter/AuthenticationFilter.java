package com.epam.cdp.jee.filter;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Vladislav on 25.09.2016.
 */
@WebFilter(urlPatterns = {"/app.html", "/new.html"})
public class AuthenticationFilter implements Filter{

    @Inject
    private Logger log;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();

        if (session != null) {
            String username = Optional.ofNullable((String)session.getAttribute("username")).orElse("");

            if (username.isEmpty()) {
                 response.sendRedirect(request.getContextPath()+"/errors/unauthorised.html");
            } else {
                log.info("User '"+username+"' is authenticated.");
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

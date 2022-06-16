package com.order.management.ordermanagement.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.management.ordermanagement.model.ErrorDetails;
import com.order.management.ordermanagement.repository.user.UserLogin;
import com.order.management.ordermanagement.repository.user.UserLoginRepository;
import com.order.management.ordermanagement.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthHandler implements Filter {

    @Value("${app.auth.header}")
    private String authHeader;

    @Value("${app.auth.token}")
    private String authToken;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String message = null;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getServletPath().equals("/login") || (httpRequest.getServletPath().equals("/test"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Cookie[] cookies = httpRequest.getCookies();
            String user = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    user = cookie.getValue();
                }
            }
            // TODO :: Move this out of this layer in real prod app and get user from DB
            if (cookies.length > 0 && user != null) {

                if (authToken.equals(httpRequest.getHeader(authHeader))) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    message = "Forbidden. Invalid token";
                    servletResponse
                            .getWriter()
                            .write(new ObjectMapper().writeValueAsString(new ErrorDetails("403", message)));
                    servletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
                    httpResponse.setStatus(403);
                }
            } else {
                message = "Forbidden. Invalid token";
                servletResponse
                        .getWriter()
                        .write(new ObjectMapper().writeValueAsString(new ErrorDetails("403", message)));
                servletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
                httpResponse.setStatus(403);
            }
        }
    }
}
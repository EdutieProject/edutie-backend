package com.edutie.edutiebackend.infrastucture.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class KeyCloackLogoutHandler implements LogoutHandler {
    private static final Logger LOG = LoggerFactory.getLogger(KeyCloackLogoutHandler.class);


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        HttpSession session = request.getSession(false);
        SecurityContext context = SecurityContextHolder.getContext();
        if (session != null) {
            session.invalidate();
        }
        context.setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
    private void logoutFromKeyCloack(HttpServletRequest request, HttpServletResponse response) {
        try {
            //String logoutUrl = "http://localhost:8080/auth/realms/edutie/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/";
            //lokalizacja na frontend
            String logoutUrl = "http://localhost:3000/";
            response.sendRedirect(logoutUrl);
        } catch (IOException e) {
            LOG.error("Error while redirecting to logout page", e);
        }
    }
}

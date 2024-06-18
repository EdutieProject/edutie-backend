package com.edutie.backend.infrastucture.authentication.implementation;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class KeyCloackLogoutHandler implements LogoutHandler {
    private static final Logger LOG = LoggerFactory.getLogger(KeyCloackLogoutHandler.class);
    
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

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        HttpSession session = request.getSession(false);
        SecurityContext context = SecurityContextHolder.getContext();
        Optional<HttpSession> isSessionNull=Optional.ofNullable(session);


        if (isSessionNull.isPresent()) //returns true if the Optional contains a non-null value and false otherwise.
        {
            session.invalidate();
        }
        else
        {
            //rzucenie komunikatu, że nie można obsłużyć logouta
        }
        context.setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
    
}

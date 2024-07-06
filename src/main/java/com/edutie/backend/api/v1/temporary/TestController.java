package com.edutie.backend.api.v1.temporary;

import com.edutie.backend.api.common.AuthenticationError;
import com.edutie.backend.domain.administration.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validation.Result;

import java.util.UUID;

@RestController
@RequestMapping("/auth-test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/test-authentication")
    public String authenticated(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return Result.failure(AuthenticationError.invalidAuthentication()).toString();
        }
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            UserId userId = new UserId(UUID.fromString(jwtAuthenticationToken.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
            return "AUTH SUCCESS! Look, its you: " + userId;
        }
        return Result.failure(AuthenticationError.noJwtAuthentication()).toString();
    }
}

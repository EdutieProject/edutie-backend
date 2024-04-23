package com.edutie.backend.infrastucture.authentication.implementation;

import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.domain.administration.UserId;
import org.keycloak.representations.JsonWebToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationPlaceholderImplementation implements AuthenticationPlaceholder {
    @Override
    public UserId authenticateUser(JsonWebToken jwt) {
        return new UserId(UUID.fromString("6e6fbfaf-5711-44fd-aec8-a0ea8148272c"));
    }
}

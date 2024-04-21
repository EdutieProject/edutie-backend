package com.edutie.backend.infrastucture.authentication.ports;

import com.edutie.backend.domain.administration.UserId;
import org.keycloak.representations.JsonWebToken;

public interface AuthenticationPlaceholder {
    UserId authenticateUser(JsonWebToken jwt);
}

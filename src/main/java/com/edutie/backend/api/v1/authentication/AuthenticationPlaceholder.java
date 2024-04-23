package com.edutie.backend.api.v1.authentication;

import com.edutie.backend.domain.administration.UserId;
import org.keycloak.representations.JsonWebToken;

public interface AuthenticationPlaceholder {
    UserId authenticateUser(JsonWebToken jwt);
}

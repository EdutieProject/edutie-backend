package com.edutie.backend.api.authentication;

import com.edutie.backend.domain.administration.UserId;
import org.springframework.security.core.Authentication;

public interface AuthenticationPlaceholder {
    UserId authenticateUser(Authentication jwt);
}

package com.edutie.backend.infrastructure.authorization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.infrastucture.authorization.administrator.AdministratorAuthorization;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AdministratorAuthorizationTests {
    @Autowired
    AdministratorAuthorization administratorAuthorization;

    @Autowired
    AdministratorPersistence administratorPersistence;

    private final UserId userId = new UserId(UUID.fromString("6f3ed855-4716-4f8e-a42e-adf7a0c3273c"));

    @Test
    public void authorizeFailureTest() {
        assert administratorAuthorization.authorize(new UserId()).isFailure();
    }

    @Test
    public void roleInjectionSuccessTest() {
        var jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", userId.identifierValue().toString())
                .build();
        var authorities = AuthorityUtils.createAuthorityList("edutie-admin");
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt, authorities);
        administratorAuthorization.injectRoles(jwtAuthenticationToken);

        assert administratorPersistence.getByAuthorizedUserId(userId) != null;
    }

    @Test
    public void roleInjectionFailureTest() {
        var jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", userId.identifierValue().toString())
                .build();
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);
        administratorAuthorization.injectRoles(jwtAuthenticationToken);

        assertThrows(Exception.class, () -> administratorPersistence.getByAuthorizedUserId(userId));
    }

}

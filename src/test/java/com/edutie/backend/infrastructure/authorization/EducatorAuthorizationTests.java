package com.edutie.backend.infrastructure.authorization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
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
public class EducatorAuthorizationTests {
    @Autowired
    EducatorAuthorization educatorAuthorization;

    @Autowired
    EducatorPersistence educatorPersistence;

    private final UserId userId = new UserId(UUID.fromString("6f3ed855-4716-4f8e-a42e-adf7a0c3273c"));

    @Test
    public void authorizeFailureTest() {
        assert educatorAuthorization.authorize(new UserId()).isFailure();
    }


    private static class RealmAccess {
        @JsonProperty
        public final List<String> roles;

        public RealmAccess(String... roles) {
            this.roles = Arrays.stream(roles).toList();
        }
    }

    @Test
    public void roleInjectionSuccessTest() {
        var jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", userId.identifierValue().toString())
                .build();
        var authorities = AuthorityUtils.createAuthorityList("edutie-admin");
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt, authorities);
        educatorAuthorization.injectRoles(jwtAuthenticationToken);

        assert educatorPersistence.getByAuthorizedUserId(userId) != null;
    }

    @Test
    public void roleInjectionFailureTest() {
        var jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", userId.identifierValue().toString())
                .build();
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);
        educatorAuthorization.injectRoles(jwtAuthenticationToken);

        assertThrows(Exception.class, () -> educatorPersistence.getByAuthorizedUserId(userId));
    }

}

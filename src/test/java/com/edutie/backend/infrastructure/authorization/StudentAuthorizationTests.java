package com.edutie.backend.infrastructure.authorization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
public class StudentAuthorizationTests {
    @Autowired
    StudentAuthorization studentAuthorization;

    @Autowired
    StudentPersistence studentPersistence;

    private final UserId userId = new UserId(UUID.fromString("6f3ed855-4716-4f8e-a42e-adf7a0c3273c"));
    @Test
    public void authorizeFailureTest() {
        assert studentAuthorization.authorize(userId).isFailure();
    }

    @Test
    public void roleInjectionTest() {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(
                new Jwt("helloWorld!",
                        Instant.now(), Instant.MAX, new HashMap<>(){{put("head", "none");}},
                        new HashMap<>(){{put("sub", userId.identifierValue().toString());}})
        );

        studentAuthorization.injectRoles(jwtAuthenticationToken);

        assert studentPersistence.getByAuthorizedUserId(userId) != null;

    }

}

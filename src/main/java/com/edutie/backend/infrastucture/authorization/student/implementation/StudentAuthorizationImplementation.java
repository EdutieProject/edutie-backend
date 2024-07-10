package com.edutie.backend.infrastucture.authorization.student.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentAuthorizationImplementation implements StudentAuthorization {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;

    @Override
    public Result authorize(UserId userId) {
        return studentRepository.findByOwnerUserId(userId).isEmpty() ?
                Result.failure(AuthorizationError.roleExpected(Student.class))
                : Result.success();
    }

    /**
     * Pre-inject roles if any injectable exist in authentication token.
     *
     * @param authentication authentication token
     */
    @Override
    public void injectRoles(JwtAuthenticationToken authentication) {
        UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
        if (studentRepository.findByOwnerUserId(userId).isEmpty()) {
            LOGGER.info("No student role profile found in edutie context. Injecting student role in the edutie db.");
            Student studentProfile = Student.create(userId);
            studentRepository.save(studentProfile);
            return;
        }
        LOGGER.debug("Student role already exits. Not injecting student role for this request.");
    }
}

package com.edutie.backend.infrastucture.authentication.implementation;

import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationPlaceholderImplementation implements AuthenticationPlaceholder {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final EducatorRepository educatorRepository;

    private boolean profilesExist(UserId userId) {
        LOGGER.info("Checking if profiles for the user exist...");
        return studentRepository.findByOwnerUserId(userId).isPresent() && educatorRepository.findByOwnerUserId(userId).isPresent();
    }

    private void initializeProfiles(UserId userId) {
        LOGGER.info("Initializing profiles for user {}", userId.identifierValue());
        Student studentProfile = Student.create(userId);
        studentRepository.save(studentProfile);
        Educator educatorProfile = Educator.create(userId, new AdminId());
        educatorRepository.save(educatorProfile);
    }

    @Override
    public UserId authenticateUser(Authentication jwt) {
        UserId userId = new UserId(UUID.fromString("6e6fbfaf-5711-44fd-aec8-a0ea8148272c"));
        if (!profilesExist(userId)) {
            LOGGER.info("Profiles for {} user do not exist.", userId.identifierValue());
            initializeProfiles(userId);
        }
        return userId;
    }
}

package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EducatorPersistenceImplementationTest {
    private final UserId userId = new UserId();
    @Autowired
    EducatorRepository educatorRepository;
    @Autowired
    private EducatorPersistenceImplementation educatorPersistenceImplementation;

    @Test
    void getByAuthorizedUserId() {
        Educator educator = Educator.create(userId);
        educatorRepository.save(educator);

        Educator educator1 = educatorPersistenceImplementation.getByAuthorizedUserId(userId);
        assertNotNull(educator1);
    }

    @Test
    void removeForUser() {
        Educator educator = Educator.create(userId);
        educatorRepository.save(educator);

        educatorPersistenceImplementation.removeForUser(userId).throwIfFailure();

        assertFalse(educatorRepository.findByOwnerUserId(userId).isPresent());
    }
}
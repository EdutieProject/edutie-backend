package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministratorPersistenceImplementationTest {
    private final UserId userId = new UserId();
    @Autowired
    AdministratorRepository administratorRepository;
    @Autowired
    private AdministratorPersistenceImplementation administratorPersistenceImplementation;

    @Test
    void getByAuthorizedUserId() {
        Administrator administrator = Administrator.create(userId);
        administratorRepository.save(administrator);

        Administrator administrator1 = administratorPersistenceImplementation.getByAuthorizedUserId(userId);
        assertNotNull(administrator1);
    }

    @Test
    void removeForUser() {
        Administrator administrator = Administrator.create(userId);
        administratorRepository.save(administrator);

        administratorPersistenceImplementation.removeForUser(userId).throwIfFailure();

        assertFalse(administratorRepository.findByOwnerUserId(userId).isPresent());
    }
}
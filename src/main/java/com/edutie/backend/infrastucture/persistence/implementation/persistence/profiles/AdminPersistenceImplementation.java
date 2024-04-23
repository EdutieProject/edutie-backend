package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.AdminPersistence;
import com.edutie.backend.domain.administration.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdminPersistenceImplementation implements AdminPersistence {
    @Override
    public AdminId getAdminId(UserId userId) {
        return new AdminId(UUID.fromString("3643fc24-fa3c-46b5-b09b-5d601801f920"));
    }
}

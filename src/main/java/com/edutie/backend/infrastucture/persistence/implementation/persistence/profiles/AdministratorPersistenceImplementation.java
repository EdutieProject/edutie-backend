package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.common.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdministratorPersistenceImplementation implements AdministratorPersistence {
    @Override
    public AdministratorId getAdminId(UserId userId) {
        return new AdministratorId(UUID.fromString("3643fc24-fa3c-46b5-b09b-5d601801f920"));
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Administrator> entityClass() {
        return Administrator.class;
    }

    @Override
    public RoleRepository<Administrator, AdministratorId> getRepository() {
        return null;
    }
}

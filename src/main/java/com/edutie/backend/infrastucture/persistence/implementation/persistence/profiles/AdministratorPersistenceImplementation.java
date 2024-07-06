package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.AdministratorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.common.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdministratorPersistenceImplementation implements AdministratorPersistence {
    private final AdministratorRepository administratorRepository;
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
        return administratorRepository;
    }
}

package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.identities.AdministratorId;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.infrastructure.persistence.base.DefaultRolePersistence;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministratorPersistenceImplementation
        extends DefaultRolePersistence<Administrator, AdministratorId> implements AdministratorPersistence {
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

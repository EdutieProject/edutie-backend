package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.infrastructure.persistence.base.DefaultRolePersistence;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducatorPersistenceImplementation
        extends DefaultRolePersistence<Educator, EducatorId> implements EducatorPersistence {
    private final EducatorRepository educatorRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public RoleRepository<Educator, EducatorId> getRepository() {
        return educatorRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Educator> entityClass() {
        return Educator.class;
    }

}

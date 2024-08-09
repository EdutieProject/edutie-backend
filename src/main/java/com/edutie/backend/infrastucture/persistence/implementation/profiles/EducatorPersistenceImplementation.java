package com.edutie.backend.infrastucture.persistence.implementation.profiles;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducatorPersistenceImplementation implements EducatorPersistence {
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

package com.edutie.backend.infrastucture.persistence.implementation.persistence.personalization;

import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LearningResourceDefinitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearningResourceDefinitionPersistenceImplementation implements LearningResourceDefinitionPersistence {
    private final LearningResourceDefinitionRepository learningResourceDefinitionRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningResourceDefinition, LearningResourceDefinitionId> getRepository() {
        return learningResourceDefinitionRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningResourceDefinition> entityClass() {
        return LearningResourceDefinition.class;
    }
}

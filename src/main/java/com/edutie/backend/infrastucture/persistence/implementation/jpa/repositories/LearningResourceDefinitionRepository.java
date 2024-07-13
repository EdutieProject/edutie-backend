package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningResourceDefinitionRepository extends JpaRepository<LearningResourceDefinition, LearningResourceDefinitionId> {
}

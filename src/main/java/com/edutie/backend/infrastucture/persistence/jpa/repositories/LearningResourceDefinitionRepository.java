package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningResourceDefinitionRepository extends JpaRepository<LearningResourceDefinition, LearningResourceDefinitionId> {
    List<LearningResourceDefinition> getByAuthorEducator(Educator educator);
}

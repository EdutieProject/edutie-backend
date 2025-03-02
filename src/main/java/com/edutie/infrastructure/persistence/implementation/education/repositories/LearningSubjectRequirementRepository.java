package com.edutie.infrastructure.persistence.implementation.education.repositories;

import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningSubjectRequirementRepository extends JpaRepository<LearningSubjectRequirement, ElementalRequirementId> {
}

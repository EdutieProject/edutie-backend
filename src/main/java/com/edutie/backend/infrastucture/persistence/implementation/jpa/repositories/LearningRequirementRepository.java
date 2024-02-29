package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> {
}

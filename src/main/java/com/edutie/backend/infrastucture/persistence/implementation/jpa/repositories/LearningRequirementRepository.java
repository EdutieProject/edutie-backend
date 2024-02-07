package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.core.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> {
}

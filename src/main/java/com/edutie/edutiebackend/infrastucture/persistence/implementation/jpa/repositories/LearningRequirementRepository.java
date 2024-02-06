package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.edutiebackend.domain.core.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> {
}

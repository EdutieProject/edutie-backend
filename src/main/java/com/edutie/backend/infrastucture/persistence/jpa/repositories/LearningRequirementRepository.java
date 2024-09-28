package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.*;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> { }

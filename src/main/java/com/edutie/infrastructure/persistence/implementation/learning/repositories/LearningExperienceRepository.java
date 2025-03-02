package com.edutie.infrastructure.persistence.implementation.learning.repositories;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import org.springframework.data.jpa.repository.*;

public interface LearningExperienceRepository extends JpaRepository<LearningExperience<?>, LearningExperienceId> {
}

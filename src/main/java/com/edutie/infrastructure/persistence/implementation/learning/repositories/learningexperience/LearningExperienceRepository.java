package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningExperienceRepository
        extends JpaRepository<LearningExperience<?>, LearningExperienceId> {
}

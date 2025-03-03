package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience;

import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.implementations.StoryBasedActivityLearningExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryBasedActivityLearningExperienceRepository
        extends JpaRepository<StoryBasedActivityLearningExperience, LearningExperienceId> {
}

package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult;

import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.learningresult.implementations.StoryBasedActivityLearningResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryBasedActivityLearningResultRepository extends JpaRepository<StoryBasedActivityLearningResult, LearningResultId> {
}

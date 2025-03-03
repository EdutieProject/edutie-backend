package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult;

import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.learningresult.implementations.ScenarioProblemSolvingActivityLearningResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioProblemSolvingActivityLearningResultRepository extends JpaRepository<ScenarioProblemSolvingActivityLearningResult, LearningResultId> {
}

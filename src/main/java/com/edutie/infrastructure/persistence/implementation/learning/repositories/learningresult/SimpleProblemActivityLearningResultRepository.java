package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult;

import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.learningresult.implementations.SimpleProblemActivityLearningResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleProblemActivityLearningResultRepository extends JpaRepository<SimpleProblemActivityLearningResult, LearningResultId> {
}

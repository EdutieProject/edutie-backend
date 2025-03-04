package com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningResultRepository
        extends JpaRepository<LearningResult<?>, LearningResultId> {
}

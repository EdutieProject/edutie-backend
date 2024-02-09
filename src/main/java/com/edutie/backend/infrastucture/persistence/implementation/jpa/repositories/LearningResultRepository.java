package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
}

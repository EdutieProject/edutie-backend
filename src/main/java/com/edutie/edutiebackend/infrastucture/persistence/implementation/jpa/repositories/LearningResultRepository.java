package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
}

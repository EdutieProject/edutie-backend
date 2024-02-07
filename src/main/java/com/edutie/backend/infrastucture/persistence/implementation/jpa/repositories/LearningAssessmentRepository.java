package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.backend.domain.core.learningresult.identities.AssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningAssessmentRepository extends JpaRepository<LearningAssessment, AssessmentId> {
}

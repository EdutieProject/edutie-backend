package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.learner.learningresult.entities.SkillAssessment;
import com.edutie.backend.domain.learner.learningresult.identities.AssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment, AssessmentId> {
}

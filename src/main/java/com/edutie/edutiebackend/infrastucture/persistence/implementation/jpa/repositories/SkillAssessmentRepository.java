package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.identities.AssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment, AssessmentId> {
}

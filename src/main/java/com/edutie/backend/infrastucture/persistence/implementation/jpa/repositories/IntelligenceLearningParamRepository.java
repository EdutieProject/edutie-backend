package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.domain.core.student.identities.LearningParameterId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceLearningParamRepository extends JpaRepository<IntelligenceLearningParameter, LearningParameterId> {
}

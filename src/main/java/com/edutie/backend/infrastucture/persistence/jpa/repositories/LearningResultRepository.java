package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import org.springframework.data.jpa.repository.*;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> { }

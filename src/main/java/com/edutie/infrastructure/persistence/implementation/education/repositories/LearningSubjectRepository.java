package com.edutie.infrastructure.persistence.implementation.education.repositories;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import org.springframework.data.jpa.repository.*;

public interface LearningSubjectRepository extends JpaRepository<LearningSubject, LearningSubjectId> {
}

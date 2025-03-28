package com.edutie.infrastructure.persistence.implementation.education.repositories;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearningSubjectRepository extends JpaRepository<LearningSubject, LearningSubjectId> {
    List<LearningSubject> findAllByAuthorEducator(Educator educator);
    Optional<LearningSubject> findLearningSubjectByRequirementsContaining(LearningSubjectRequirement requirement);
}

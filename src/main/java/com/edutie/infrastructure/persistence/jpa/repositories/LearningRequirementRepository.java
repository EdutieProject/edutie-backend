package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningRequirementRepository extends JpaRepository<LearningSubject, LearningRequirementId> {
    List<LearningSubject> findByKnowledgeSubjectId(KnowledgeSubjectId knowledgeSubjectId);

    @Query(value = "SELECT lreq FROM LearningSubject lreq ORDER BY FUNCTION('RANDOM') LIMIT :number")
    List<LearningSubject> findRandom(@Param("number") int max);
}

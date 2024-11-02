package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> {
    List<LearningRequirement> findByKnowledgeSubjectId(KnowledgeSubjectId knowledgeSubjectId);

    @Query(value = "SELECT lreq FROM LearningRequirement lreq ORDER BY FUNCTION('RANDOM') LIMIT :number")
    List<LearningRequirement> findRandom(@Param("number") int max);
}

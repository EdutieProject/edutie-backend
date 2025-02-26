package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.domain.core.education.learningrequirement.identities.LearningRequirementId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningRequirementRepository extends JpaRepository<LearningRequirement, LearningRequirementId> {
    List<LearningRequirement> findByKnowledgeSubjectId(KnowledgeSubjectId knowledgeSubjectId);

    @Query(value = "SELECT lreq FROM LearningRequirement lreq ORDER BY FUNCTION('RANDOM') LIMIT :number")
    List<LearningRequirement> findRandom(@Param("number") int max);
}

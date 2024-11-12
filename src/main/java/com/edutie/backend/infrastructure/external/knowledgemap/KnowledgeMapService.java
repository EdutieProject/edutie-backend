package com.edutie.backend.infrastructure.external.knowledgemap;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.infrastructure.external.common.ExternalService;
import validation.WrapperResult;

import java.util.Set;

/**
 * Service for interaction with externally implemented Knowledge Map.
 */
public interface KnowledgeMapService extends ExternalService {
    // Old contract
    WrapperResult<Set<KnowledgeCorrelation>> getKnowledgeCorrelations(Set<KnowledgeSubjectId> knowledgeSubjectIds);

    // New contract
    WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(
            Set<LearningRequirement> sourceRequirements,
            Set<LearningRequirement> comparedLearningRequirements
    );
}

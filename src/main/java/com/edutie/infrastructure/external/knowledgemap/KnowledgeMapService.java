package com.edutie.infrastructure.external.knowledgemap;

import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.infrastructure.external.common.ExternalService;
import validation.WrapperResult;

import java.util.Set;

/**
 * Service for interaction with externally implemented Knowledge Map.
 */
public interface KnowledgeMapService extends ExternalService {
    /**
     * Retrieves the correlations between the provided learning requirements.
     *
     * @param sourceRequirements           source learning requirements
     * @param comparedLearningRequirements learning requirements to measure the correlation
     * @return Result wrapping set of learning req. correlations
     */
    WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(
            Set<LearningRequirement> sourceRequirements,
            Set<LearningRequirement> comparedLearningRequirements
    );

    /**
     * Retrieves the closest associated knowledge subject. This method does NOT retrieve a knowledge subject by id.
     * It retrieves the correlated subject instead.
     *
     * @param knowledgeSubjectId knowledge subject id.
     * @return Result of the Knowledge subject that may be of high correlation to the subject of provided id.
     */
    WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId);
}

package com.edutie.backend.infrastructure.external.knowledgemap.implementation;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.infrastructure.external.common.ExternalInfrastructureHandler;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastructure.external.knowledgemap.messages.LearningRequirementCorrelationsRequest;
import com.edutie.backend.infrastructure.external.knowledgemap.messages.LearningRequirementsCorrelationResponse;
import com.edutie.backend.infrastructure.external.knowledgemap.messages.MostCorrelatedSubjectRequest;
import com.edutie.backend.infrastructure.external.knowledgemap.messages.MostCorrelatedSubjectResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Component
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(Set<LearningRequirement> sourceRequirements, Set<LearningRequirement> comparedLearningRequirements) {
        final String LEARNING_REQUIREMENTS_CORRELATIONS_URL = KNOWLEDGE_MAP_URL + "/correlations/learning-requirements";
        return new ExternalInfrastructureHandler<Set<LearningRequirementCorrelation>, LearningRequirementCorrelationsRequest, LearningRequirementsCorrelationResponse>(this.getClass())
                .setActionUrl(LEARNING_REQUIREMENTS_CORRELATIONS_URL)
                .handle(new LearningRequirementCorrelationsRequest(sourceRequirements, comparedLearningRequirements), LearningRequirementsCorrelationResponse.class);
    }

    @Override
    public WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
        final String MOST_CORRELATED_KNOWLEDGE_SUBJECT_URL = KNOWLEDGE_MAP_URL + "/knowledge-subjects/most-correlated";
        return new ExternalInfrastructureHandler<KnowledgeSubject, MostCorrelatedSubjectRequest, MostCorrelatedSubjectResponse>(this.getClass())
                .setActionUrl(MOST_CORRELATED_KNOWLEDGE_SUBJECT_URL)
                .handle(new MostCorrelatedSubjectRequest(knowledgeSubjectId), MostCorrelatedSubjectResponse.class);
    }
}

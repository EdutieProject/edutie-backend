package com.edutie.infrastructure.external.knowledgemap.implementation;

import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.infrastructure.external.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.infrastructure.external.knowledgemap.messages.LearningRequirementCorrelationsRequest;
import com.edutie.infrastructure.external.knowledgemap.messages.LearningRequirementsCorrelationResponse;
import com.edutie.infrastructure.external.knowledgemap.messages.MostCorrelatedSubjectRequest;
import com.edutie.infrastructure.external.knowledgemap.messages.MostCorrelatedSubjectResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Component
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(Set<LearningSubject> sourceRequirements, Set<LearningSubject> comparedLearningSubjects) {
        final String LEARNING_REQUIREMENTS_CORRELATIONS_URL = KNOWLEDGE_MAP_URL + "/correlations/learning-requirements";
        return new ExternalInfrastructureHandler<Set<LearningRequirementCorrelation>, LearningRequirementCorrelationsRequest, LearningRequirementsCorrelationResponse>(this.getClass())
                .setActionUrl(LEARNING_REQUIREMENTS_CORRELATIONS_URL)
                .handle(new LearningRequirementCorrelationsRequest(sourceRequirements, comparedLearningSubjects), LearningRequirementsCorrelationResponse.class);
    }

    @Override
    public WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
        final String MOST_CORRELATED_KNOWLEDGE_SUBJECT_URL = KNOWLEDGE_MAP_URL + "/knowledge-subjects/most-correlated";
        return new ExternalInfrastructureHandler<KnowledgeSubject, MostCorrelatedSubjectRequest, MostCorrelatedSubjectResponse>(this.getClass())
                .setActionUrl(MOST_CORRELATED_KNOWLEDGE_SUBJECT_URL)
                .handle(new MostCorrelatedSubjectRequest(knowledgeSubjectId), MostCorrelatedSubjectResponse.class);
    }
}

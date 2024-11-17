package com.edutie.backend.infrastructure.external.knowledgemap.implementation;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.WrapperResult;

import java.util.Set;

@Component
@Slf4j
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    @Value("${knowledge-map-host}")
    private String KNOWLEDGE_MAP_HOST;

    @Override
    public WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(Set<LearningRequirement> sourceRequirements, Set<LearningRequirement> comparedLearningRequirements) {
        return WrapperResult.failureWrapper(new Error("NOT-IMPLEMENTED-503", ""));
    }

    @Override
    public WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
        return WrapperResult.failureWrapper(new Error("NOT-IMPLEMENTED-503", ""));
    }
}

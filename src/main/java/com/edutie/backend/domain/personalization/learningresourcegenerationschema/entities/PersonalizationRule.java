package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonalizationRule {
    private final KnowledgeSubjectId relatedKnowledgeSubjectId;
    private final int knowledgeCorrelationFactor;
    private final LearningRequirementId learningRequirementId;
    private final int qualifiedSubRequirementsAmount;
    private final List<LearningResult> learningResults;

    public PersonalizationRule(KnowledgeCorrelation knowledgeCorrelation, LearningRequirementId learningRequirementId, List<LearningResult> learningResults, int subRequirementsAmount) {
        relatedKnowledgeSubjectId = knowledgeCorrelation.getKnowledgeSubjectId();
        knowledgeCorrelationFactor = knowledgeCorrelation.getCorrelationFactor();
        this.learningRequirementId = learningRequirementId;
        this.learningResults = learningResults;

        int allSubRequirementsSize =
        this.qualifiedSubRequirementsAmount = qualifiedSubRequirementsAmount;

    }

    public void addLearningResultReference(LearningResult learningResult) {
        learningResults.add(learningResult);
    }
}

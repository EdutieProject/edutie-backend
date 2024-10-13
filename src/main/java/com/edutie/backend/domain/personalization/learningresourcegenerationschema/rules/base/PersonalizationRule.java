package com.edutie.backend.domain.personalization.learningresourcegenerationschema.rules.base;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;

import java.util.List;

public class PersonalizationRule {
    private KnowledgeCorrelation knowledgeCorrelation;
    private List<Assessment> relatedAssessments;

    public static PersonalizationRule create(KnowledgeCorrelation knowledgeCorrelation, List<Assessment> relatedAssessments) {
        PersonalizationRule rule = new PersonalizationRule();
        rule.knowledgeCorrelation = knowledgeCorrelation;
        rule.relatedAssessments = relatedAssessments;
        return rule;
    }
}

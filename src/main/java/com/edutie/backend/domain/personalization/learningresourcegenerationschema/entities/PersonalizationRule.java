package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities.PersonalizationRuleId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
public class PersonalizationRule extends EntityBase<PersonalizationRuleId> {
    private KnowledgeSubjectId relatedKnowledgeSubjectId;
    private int knowledgeCorrelationFactor;
    private List<LearningResult> learningResults;

    public static PersonalizationRule create(LearningRequirementId sourceLearningReqId, KnowledgeCorrelation knowledgeCorrelation, Student student) {
        PersonalizationRule personalizationRule = new PersonalizationRule();
        personalizationRule.setLearningResults(student.getLearningHistoryByLearningRequirement(sourceLearningReqId));
        personalizationRule.setRelatedKnowledgeSubjectId(knowledgeCorrelation.getKnowledgeSubjectId());
        personalizationRule.setKnowledgeCorrelationFactor(knowledgeCorrelation.getCorrelationFactor());
        return personalizationRule;
    }
}

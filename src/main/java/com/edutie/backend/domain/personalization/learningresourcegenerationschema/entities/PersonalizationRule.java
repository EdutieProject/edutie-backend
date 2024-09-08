package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities.PersonalizationRuleId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
public class PersonalizationRule extends EntityBase<PersonalizationRuleId> {
	private KnowledgeSubjectId relatedKnowledgeSubjectId;
	private int knowledgeCorrelationFactor;
	private List<LearningResult> learningResults;

	public static PersonalizationRule create(KnowledgeCorrelation knowledgeCorrelation, Student student) {
		PersonalizationRule personalizationRule = new PersonalizationRule();
		personalizationRule.setId(new PersonalizationRuleId());
		personalizationRule.setLearningResults(student.getLearningHistoryByKnowledgeSubject(knowledgeCorrelation.getKnowledgeSubjectId()));
		personalizationRule.setRelatedKnowledgeSubjectId(knowledgeCorrelation.getKnowledgeSubjectId());
		personalizationRule.setKnowledgeCorrelationFactor(knowledgeCorrelation.getCorrelationFactor());
		return personalizationRule;
	}
}

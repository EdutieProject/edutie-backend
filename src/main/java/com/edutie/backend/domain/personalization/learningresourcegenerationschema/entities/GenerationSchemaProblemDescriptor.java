package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.common.problemdescriptor.ProblemDescriptor;
import com.edutie.backend.domain.personalization.common.problemdescriptor.identities.ProblemDescriptorId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains data about the problems that should be generated for the Learning Resource.
 * This problem descriptor describes a problem for the LLM.
 */
@Getter
public class GenerationSchemaProblemDescriptor extends ProblemDescriptor {
	private final KnowledgeSubjectId knowledgeSubjectId;
	private final List<PersonalizationRule> personalizationRules = new ArrayList<>();

	public GenerationSchemaProblemDescriptor(LearningRequirement learningRequirement) {
		this.setId(new ProblemDescriptorId());
		this.setLearningRequirementId(learningRequirement.getId());
		this.knowledgeSubjectId = learningRequirement.getKnowledgeSubjectId();
	}

	public void addPersonalizationRule(PersonalizationRule personalizationRule) {
		personalizationRules.add(personalizationRule);
	}

	/**
	 * Mutates the problem descriptor assigning it the calculated qualified sub-requirements
	 * amount.
	 */
	public void calculateQualifiedSubRequirements(int subRequirementsSize) {
		double meanOverallGrade = personalizationRules.stream().flatMap(o -> o.getLearningResults().stream())
				.flatMap(o -> o.getAssessments().stream())
				.map(o -> o.getGrade().gradeNumber())
				.mapToInt(Integer::intValue).average().orElse(1);
		double gradePercentage = meanOverallGrade / Grade.MAX_GRADE.gradeNumber();
		this.setQualifiedSubRequirementOrdinal((int) Math.ceil(gradePercentage * subRequirementsSize));
	}
}

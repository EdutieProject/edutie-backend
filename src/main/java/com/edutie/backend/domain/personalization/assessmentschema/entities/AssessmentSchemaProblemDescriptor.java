package com.edutie.backend.domain.personalization.assessmentschema.entities;

import com.edutie.backend.domain.personalization.common.problemdescriptor.ProblemDescriptor;
import com.edutie.backend.domain.personalization.common.problemdescriptor.identities.ProblemDescriptorId;
import lombok.*;

/**
 * Provides problem descriptors for assessment schema with necessary information about
 * the problem encompassed in Learning Resource that is being assessed.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AssessmentSchemaProblemDescriptor extends ProblemDescriptor {

	public AssessmentSchemaProblemDescriptor(ProblemDescriptor learningResourceProblemDescriptor) {
		this.setId(new ProblemDescriptorId());
		this.setLearningRequirementId(learningResourceProblemDescriptor.getLearningRequirementId());
		this.setQualifiedSubRequirementOrdinal(learningResourceProblemDescriptor.getQualifiedSubRequirementOrdinal());
	}
}

package com.edutie.backend.domain.personalization.assessmentschema.entities;

import com.edutie.backend.domain.personalization.common.problemdescriptor.ProblemDescriptor;
import com.edutie.backend.domain.personalization.common.problemdescriptor.identities.ProblemDescriptorId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AssessmentSchemaProblemDescriptor extends ProblemDescriptor {

    public AssessmentSchemaProblemDescriptor(ProblemDescriptor learningResourceProblemDescriptor) {
        this.setId(new ProblemDescriptorId());
        this.setLearningRequirementId(learningResourceProblemDescriptor.getLearningRequirementId());
        this.setQualifiedSubRequirementOrdinal(learningResourceProblemDescriptor.getQualifiedSubRequirementOrdinal());
    }
}

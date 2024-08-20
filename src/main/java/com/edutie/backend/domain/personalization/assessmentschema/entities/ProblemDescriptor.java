package com.edutie.backend.domain.personalization.assessmentschema.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.assessmentschema.identities.ProblemDescriptorId;
import com.edutie.backend.domain.personalization.learningresource.entities.ProblemDetail;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProblemDescriptor extends EntityBase<ProblemDescriptorId> {
    private final LearningRequirementId learningRequirementId;
    private final int qualifiedSubRequirements;

    public ProblemDescriptor(ProblemDetail problemDetail) {
        this.setId(new ProblemDescriptorId());
        this.learningRequirementId = problemDetail.getLearningRequirementId();
        this.qualifiedSubRequirements = problemDetail.getQualifiedSubRequirementsAmount();
    }
}

package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.identities.ProblemDetailId;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@Setter(AccessLevel.PROTECTED)
public class ProblemDetail extends EntityBase<ProblemDetailId> {
    private LearningRequirementId learningRequirementId;
    private int qualifiedSubRequirementsAmount;

    /**
     * Creates Problem Detail entity instance.
     * @param learningRequirementId learning requirement id
     * @param qualifiedSubRequirementsAmount qualified Sub Requirements Amount
     * @return new Problem Detail instance.
     */
    public static ProblemDetail create(LearningRequirementId learningRequirementId, int qualifiedSubRequirementsAmount) {
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.setId(new ProblemDetailId());
        problemDetail.setLearningRequirementId(learningRequirementId);
        problemDetail.setQualifiedSubRequirementsAmount(qualifiedSubRequirementsAmount);
        return problemDetail;
    }
}

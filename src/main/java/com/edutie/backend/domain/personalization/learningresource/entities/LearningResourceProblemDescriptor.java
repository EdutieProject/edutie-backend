package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.common.problemdescriptor.ProblemDescriptor;
import com.edutie.backend.domain.personalization.common.problemdescriptor.identities.ProblemDescriptorId;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@Setter(AccessLevel.PROTECTED)
public class LearningResourceProblemDescriptor extends ProblemDescriptor {

    /**
     * Creates Problem Detail entity instance.
     *
     * @param learningRequirementId          learning requirement id
     * @param qualifiedSubRequirementsAmount qualified Sub Requirements Amount
     * @return new Problem Detail instance.
     */
    public static LearningResourceProblemDescriptor create(LearningRequirementId learningRequirementId, int qualifiedSubRequirementsAmount) {
        LearningResourceProblemDescriptor learningResourceProblemDescriptor = new LearningResourceProblemDescriptor();
        learningResourceProblemDescriptor.setId(new ProblemDescriptorId());
        learningResourceProblemDescriptor.setLearningRequirementId(learningRequirementId);
        learningResourceProblemDescriptor.setQualifiedSubRequirementOrdinal(qualifiedSubRequirementsAmount);
        return learningResourceProblemDescriptor;
    }
}

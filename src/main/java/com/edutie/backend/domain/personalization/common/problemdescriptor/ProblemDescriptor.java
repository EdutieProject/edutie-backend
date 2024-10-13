package com.edutie.backend.domain.personalization.common.problemdescriptor;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.common.problemdescriptor.identities.ProblemDescriptorId;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Problem descriptor is a base class for the metadata of the problem,
 * where problem is the learning resource's representation of the learning requirement.
 */
@NoArgsConstructor
@Getter
@MappedSuperclass
@Setter(AccessLevel.PROTECTED)
public abstract class ProblemDescriptor extends EntityBase<ProblemDescriptorId> {
    private LearningRequirementId learningRequirementId;
    private int qualifiedSubRequirementOrdinal;
}

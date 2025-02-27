package com.edutie.domain.core.learning.learningexperience.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceRequirementId;
import jakarta.persistence.Entity;

/**
 * An Elemental Requirement Reference. This metadata entity serves as a reference
 * to the learning subject requirement as it has same id.
 */
@Entity
public class LearningExperienceRequirement extends EntityBase<LearningExperienceRequirementId> {
    private ElementalRequirementId elementalRequirementId;
}

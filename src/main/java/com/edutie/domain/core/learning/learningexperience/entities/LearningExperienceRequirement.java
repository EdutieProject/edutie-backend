package com.edutie.domain.core.learning.learningexperience.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceRequirementId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * An Elemental Requirement Reference. This metadata entity serves as a reference
 * to the learning subject requirement as it has same id.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Setter(AccessLevel.PRIVATE)
public class LearningExperienceRequirement extends EntityBase<LearningExperienceRequirementId> {
    private ElementalRequirementId elementalRequirementId;

    public static LearningExperienceRequirement from(ElementalRequirement elementalRequirement) {
        LearningExperienceRequirement learningExperienceRequirement = new LearningExperienceRequirement();
        learningExperienceRequirement.setId(new LearningExperienceRequirementId());
        learningExperienceRequirement.setElementalRequirementId(elementalRequirement.getId());
        return learningExperienceRequirement;
    }
}

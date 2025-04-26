package com.edutie.domain.core.learning.learningresult.entities;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ElementalRequirementSnapshot {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "elemental_requirement_id"))
    private ElementalRequirementId elementalRequirementId;
    private String title;

    public static ElementalRequirementSnapshot from(ElementalRequirement<?> elementalRequirement) {
        ElementalRequirementSnapshot elementalRequirementSnapshot = new ElementalRequirementSnapshot();
        elementalRequirementSnapshot.elementalRequirementId = elementalRequirement.getId();
        elementalRequirementSnapshot.title = elementalRequirement.getTitle();
        return elementalRequirementSnapshot;
    }
}

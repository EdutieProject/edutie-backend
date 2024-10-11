package com.edutie.backend.domain.education.learningrequirement.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.ElementalRequirementId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ElementalRequirement extends EntityBase<ElementalRequirementId> {
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "requirement_text", columnDefinition = "TEXT"))
	private PromptFragment requirementText;
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "scientific_description", columnDefinition = "TEXT"))
	private PromptFragment scientificDescription;
	private Integer ordinal;

	public static ElementalRequirement create(PromptFragment requirementText, PromptFragment scientificDescription, int orderIndex) {
		ElementalRequirement elementalRequirement = new ElementalRequirement();
		elementalRequirement.setId(new ElementalRequirementId());
		elementalRequirement.requirementText = requirementText;
		elementalRequirement.setScientificDescription(scientificDescription);
		elementalRequirement.ordinal = orderIndex;
		return elementalRequirement;
	}
}

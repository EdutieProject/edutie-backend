package com.edutie.backend.domain.education.learningrequirement.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.SubRequirementId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SubRequirement extends EntityBase<SubRequirementId> {
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "requirement_text", columnDefinition = "TEXT"))
	private PromptFragment requirementText;
	@Embedded
	@AttributeOverride(name = "text", column = @Column(name = "scientific_description", columnDefinition = "TEXT"))
	private PromptFragment scientificDescription;
	private Integer ordinal;

	public static SubRequirement create(PromptFragment requirementText, PromptFragment scientificDescription, int orderIndex) {
		SubRequirement subRequirement = new SubRequirement();
		subRequirement.setId(new SubRequirementId());
		subRequirement.requirementText = requirementText;
		subRequirement.setScientificDescription(scientificDescription);
		subRequirement.ordinal = orderIndex;
		return subRequirement;
	}
}

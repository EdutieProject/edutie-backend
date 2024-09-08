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
	@AttributeOverride(name = "text", column = @Column(name = "description", columnDefinition = "TEXT"))
	private PromptFragment description;
	private Integer ordinal;

	public static SubRequirement create(PromptFragment desc, int orderIndex) {
		SubRequirement subRequirement = new SubRequirement();
		subRequirement.setId(new SubRequirementId());
		subRequirement.description = desc;
		subRequirement.ordinal = orderIndex;
		return subRequirement;
	}
}

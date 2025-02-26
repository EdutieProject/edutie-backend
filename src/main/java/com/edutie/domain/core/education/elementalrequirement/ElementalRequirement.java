package com.edutie.domain.core.education.elementalrequirement;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class ElementalRequirement extends EntityBase<ElementalRequirementId> {
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "student_objective", columnDefinition = "TEXT"))
    protected PromptFragment studentObjective;
}

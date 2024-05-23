package com.edutie.backend.domain.education.learningrequirement.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.SubRequirementId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class SubRequirement extends EntityBase<SubRequirementId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "description"))
    private PromptFragment description;
    private Integer orderIndex;

    public static SubRequirement create(String name, PromptFragment desc, int orderIndex) {
        SubRequirement subRequirement = new SubRequirement();
        subRequirement.name = name;
        subRequirement.description = desc;
        subRequirement.orderIndex = orderIndex;
        return subRequirement;
    }
}

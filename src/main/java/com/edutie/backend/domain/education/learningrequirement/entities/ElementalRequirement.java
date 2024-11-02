package com.edutie.backend.domain.education.learningrequirement.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.ElementalRequirementId;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "learning_requirement_id")
    @JsonIgnore
    private LearningRequirement learningRequirement;

    @JsonProperty("learningRequirementId")
    public LearningRequirementId getLearningRequirementId() {
        return learningRequirement.getId();
    }

    public static ElementalRequirement create(LearningRequirement learningRequirement, PromptFragment requirementText, PromptFragment scientificDescription, int orderIndex) {
        ElementalRequirement elementalRequirement = new ElementalRequirement();
        elementalRequirement.setId(new ElementalRequirementId());
        elementalRequirement.learningRequirement = learningRequirement;
        elementalRequirement.requirementText = requirementText;
        elementalRequirement.setScientificDescription(scientificDescription);
        elementalRequirement.ordinal = orderIndex;
        return elementalRequirement;
    }
}

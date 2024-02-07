package com.edutie.backend.domain.core.learningrequirement;

import com.edutie.backend.domain.core.common.base.AuditableEntityBase;
import com.edutie.backend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.core.learningrequirement.identities.LearningRequirementId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningRequirement extends AuditableEntityBase<LearningRequirementId> {
    String name;
    PromptFragment description;

}

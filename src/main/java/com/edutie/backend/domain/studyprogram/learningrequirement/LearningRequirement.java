package com.edutie.backend.domain.studyprogram.learningrequirement;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.studyprogram.learningrequirement.identities.LearningRequirementId;
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

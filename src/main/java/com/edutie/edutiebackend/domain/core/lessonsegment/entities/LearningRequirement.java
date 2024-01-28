package com.edutie.edutiebackend.domain.core.lessonsegment.entities;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LearningRequirementId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LearningRequirement extends EntityBase<LearningRequirementId> {
    String name;
    PromptFragment description;

}

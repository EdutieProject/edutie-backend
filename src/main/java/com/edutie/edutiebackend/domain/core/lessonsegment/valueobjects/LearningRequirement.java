package com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.Priority;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LearningRequirementId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningRequirement extends EntityBase<LearningRequirementId> {
    PromptFragment description;
    Priority priority;
}

package com.edutie.backend.domain.studyprogram.exercisetype;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.exercisetype.identities.ExerciseTypeId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class responsible for defining exercise types which differ in utilized skills.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ExerciseType extends AuditableEntityBase<ExerciseTypeId> {
    private String name;
    private PromptFragment description;

    public static ExerciseType create(UserId userId) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(new ExerciseTypeId());
        exerciseType.setCreatedBy(userId);
        return exerciseType;
    }
}
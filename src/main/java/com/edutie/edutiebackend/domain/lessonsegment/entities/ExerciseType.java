package com.edutie.edutiebackend.domain.lessonsegment.entities;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.ExerciseTypeId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class responsible for defining exercise types, which
 * go together with AI generation.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExerciseType extends EntityBase<ExerciseTypeId> {
    private String name;
}
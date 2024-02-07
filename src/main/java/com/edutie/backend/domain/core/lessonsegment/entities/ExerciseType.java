package com.edutie.backend.domain.core.lessonsegment.entities;

import com.edutie.backend.domain.core.common.base.EntityBase;
import com.edutie.backend.domain.core.lessonsegment.identities.ExerciseTypeId;
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
public class ExerciseType extends EntityBase<ExerciseTypeId> {
    private String name;
    private String description;
}
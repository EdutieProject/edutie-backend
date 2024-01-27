package com.edutie.edutiebackend.domain.core.lessonsegment.entities;

import com.edutie.edutiebackend.domain.core.shared.base.EntityBase;
import com.edutie.edutiebackend.domain.core.shared.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.ExerciseTypeId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * Class responsible for defining exercise types, which
 * go together with AI generation.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExerciseType extends EntityBase<ExerciseTypeId> {
    private String name;
    Set<Ability> abilitySet;

    public void addAbility(Ability ability)
    {
        abilitySet.add(ability);
    }

    public void removeAbility(Ability ability)
    {
        abilitySet.remove(ability);
    }
}
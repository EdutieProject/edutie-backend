package com.edutie.edutiebackend.domain.core.skill.entities;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.skill.entities.base.TraitIndicator;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityIndicator extends TraitIndicator<Ability> {
}

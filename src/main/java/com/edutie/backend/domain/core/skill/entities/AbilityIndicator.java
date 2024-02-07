package com.edutie.backend.domain.core.skill.entities;

import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.skill.entities.base.TraitIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityIndicator extends TraitIndicator<Ability> {
    @Column(name = "indicator_trait")
    @Convert(converter = Ability.Converter.class)
    Ability ability;

    @Override
    public Ability getTrait() {
        return ability;
    }

    @Override
    public void setTrait(Ability ability) {
        this.ability = ability;
    }
}

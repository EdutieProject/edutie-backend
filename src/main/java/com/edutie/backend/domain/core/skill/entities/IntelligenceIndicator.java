package com.edutie.backend.domain.core.skill.entities;

import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.skill.entities.base.TraitIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceIndicator extends TraitIndicator<Intelligence> {
    @Column(name = "indicator_trait")
    @Convert(converter = Intelligence.Converter.class)
    Intelligence intelligence;

    @Override
    public Intelligence getTrait() {
        return intelligence;
    }

    @Override
    public void setTrait(Intelligence intelligence) {
        this.intelligence = intelligence;
    }
}

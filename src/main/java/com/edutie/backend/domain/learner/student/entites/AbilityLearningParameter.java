package com.edutie.backend.domain.learner.student.entites;

import com.edutie.backend.domain.common.studenttraits.Ability;
import com.edutie.backend.domain.learner.student.entites.base.LearningParameter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityLearningParameter extends LearningParameter<Ability> {

    @Column(name = "parameter_trait")
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

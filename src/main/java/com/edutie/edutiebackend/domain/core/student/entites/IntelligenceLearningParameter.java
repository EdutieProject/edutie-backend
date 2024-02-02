package com.edutie.edutiebackend.domain.core.student.entites;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.student.entites.base.LearningParameter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
//@Entity
public class IntelligenceLearningParameter extends LearningParameter<Intelligence> {

    @Column(name = "parameter_trait")
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

package com.edutie.edutiebackend.domain.core.student.entites.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.student.identities.LearningParameterId;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class LearningParameter<TTrait extends Enum<TTrait> & Serializable> extends EntityBase<LearningParameterId> {
    @Column(name = "parameter_trait")
    TTrait trait;
    @Column(name = "parameter_value")
    double value;

    public void adapt(double progressValue)
    {
        value += progressValue;
    }
}

package com.edutie.edutiebackend.domain.core.student.entites.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.student.identities.LearningParameterId;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class LearningParameter<TTrait extends Enum<TTrait> & Serializable> extends EntityBase<LearningParameterId> {
    @Column(name = "parameter_trait")
    TTrait trait;
    @Column(name = "parameter_value")
    double value;

    /**
     * Simply adds a given progress value to the current value
     * @param progressValue value to add
     */
    public void adapt(double progressValue)
    {
        value += progressValue;
    }
}

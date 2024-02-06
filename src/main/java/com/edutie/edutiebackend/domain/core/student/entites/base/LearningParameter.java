package com.edutie.edutiebackend.domain.core.student.entites.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.student.identities.LearningParameterId;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@MappedSuperclass
public abstract class LearningParameter<TTrait extends Enum<TTrait>> extends EntityBase<LearningParameterId> {
    @Setter
    @Column(name = "parameter_value", nullable = false)
    Double value = 0.0;

    public abstract TTrait getTrait();
    public abstract void setTrait(TTrait trait);

    /**
     * Simply adds a given progress value to the current value
     * @param progressValue value to add
     */
    public void adapt(double progressValue)
    {
        value += progressValue;
    }
}

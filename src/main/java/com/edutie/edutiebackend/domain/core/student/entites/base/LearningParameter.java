package com.edutie.edutiebackend.domain.core.student.entites.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.student.identities.LearningParameterId;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class LearningParameter<TTrait extends Enum<TTrait>> extends EntityBase<LearningParameterId> {
    TTrait trait;
    double value;

    public void adapt(double progressValue)
    {
        value += progressValue;
    }
}

package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.StudentTraitProfileId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * Generic trait profile made for future usage.
 * @param <TTrait> The student trait to track
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class StudentTraitProfile<TTrait> extends EntityBase<StudentTraitProfileId> {
    private HashMap<TTrait, Double> parameters;

    public void adjust(TTrait trait, double difference)
    {
        var current = parameters.get(trait);
        parameters.put(trait, current + difference);
    }

    public void set(TTrait trait, double value)
    {
        parameters.put(trait, value);
    }

}

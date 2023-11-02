package com.edutie.edutiebackend.domain.learningresource.entities;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.ExerciseId;
import com.edutie.edutiebackend.domain.learningresource.interfaces.ILearningActivity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Exercise extends EntityBase<ExerciseId> implements ILearningActivity{
    private String exerciseText;
}

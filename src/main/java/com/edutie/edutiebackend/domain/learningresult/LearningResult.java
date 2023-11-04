package com.edutie.edutiebackend.domain.learningresult;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.common.identities.LearningResultId;

import com.edutie.edutiebackend.domain.common.identities.StudentId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends EntityBase<LearningResultId> {
    LearningResourceId learningResourceId;
    StudentId studentId;

    //TODO!
    // Here would go performance measurements and stuff...
}

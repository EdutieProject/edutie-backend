package com.edutie.edutiebackend.domain.learningresult;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.common.identities.LearningResultId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends EntityBase<LearningResultId> {
    LearningResourceId learningResourceId;

    //TODO!
    // Here would go performance measurements and stuff...
}

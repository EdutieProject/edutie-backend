package com.edutie.domain.core.learning.learningresult.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningresult.identities.LearningEvaluationId;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class LearningEvaluation extends EntityBase<LearningEvaluationId> {
    @OneToMany(targetEntity = Assessment.class, fetch = FetchType.EAGER)
    private Set<Assessment> assessments = new HashSet<>();
    @Embedded
    private Feedback feedback = new Feedback();
}

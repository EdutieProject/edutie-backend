package com.edutie.domain.core.learning.learningresult.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningresult.identities.LearningEvaluationId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Getter
public class LearningEvaluation extends EntityBase<LearningEvaluationId> {
    @OneToMany(targetEntity = Assessment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Assessment> assessments = new HashSet<>();

    public static LearningEvaluation create(Set<Assessment> assessments) {
        LearningEvaluation learningEvaluation = new LearningEvaluation();
        learningEvaluation.setId(new LearningEvaluationId());
        learningEvaluation.assessments.addAll(assessments);
        return learningEvaluation;
    }
}

package com.edutie.domain.core.learning.learningresult.implementations;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.SimpleProblemActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class SimpleProblemActivityLearningResult extends LearningResult<SimpleProblemActivitySolutionSubmission> {
    public static SimpleProblemActivityLearningResult create() {
        SimpleProblemActivityLearningResult learningResult = new SimpleProblemActivityLearningResult();
        learningResult.setId(new LearningResultId());
        return learningResult;
    }
}

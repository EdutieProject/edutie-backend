package com.edutie.domain.core.learning.learningresult.entities.submission.base;

import com.edutie.domain.core.learning.common.LearningObjectiveType;

public interface ApplyingActivitySolutionSubmission extends SolutionSubmission {
    @Override
    default LearningObjectiveType getLearningType() {
        return LearningObjectiveType.APPLY;
    }
}

package com.edutie.domain.core.learning.learningresult.entities.submission.base;

import com.edutie.domain.core.learning.common.LearningObjectiveType;

public interface AnalyzingActivitySolutionSubmission extends SolutionSubmission {
    @Override
    default LearningObjectiveType getLearningType() {
        return LearningObjectiveType.ANALYZE;
    }
}

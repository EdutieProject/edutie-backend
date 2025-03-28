package com.edutie.domain.core.learning.learningresult.entities.submission.base;

import com.edutie.domain.core.learning.common.LearningObjectiveType;

public interface SolutionSubmission {
    LearningObjectiveType getLearningType();

    default String getSolutionSubmissionType() {
        return this.getClass().getSimpleName();
    }
}

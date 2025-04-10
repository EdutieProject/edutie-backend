package com.edutie.application.learning.learningresult.view;

import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;

public record LearningResultView<TSubmission extends SolutionSubmission>(
        LearningResult<TSubmission> learningResult,
        LearningSubjectId sourceId,
        String currentSourceName
) {
}

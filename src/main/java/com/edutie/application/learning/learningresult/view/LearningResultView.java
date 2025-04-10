package com.edutie.application.learning.learningresult.view;

import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.learning.learningresult.LearningResult;

public record LearningResultView(
        LearningResult<?> learningResult,
        LearningSubjectId sourceId,
        String currentSourceName
) {
}

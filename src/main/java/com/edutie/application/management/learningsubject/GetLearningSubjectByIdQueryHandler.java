package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.query.GetLearningSubjectByIdQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

public interface GetLearningSubjectByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningSubject>, GetLearningSubjectByIdQuery> {
}

package com.edutie.application.learning.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningsubject.query.GetLearningSubjectStudentViewByIdQuery;
import com.edutie.application.learning.learningsubject.view.LearningSubjectLearningView;
import validation.WrapperResult;

public interface GetLearningSubjectLearningViewByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningSubjectLearningView>, GetLearningSubjectStudentViewByIdQuery> {
}

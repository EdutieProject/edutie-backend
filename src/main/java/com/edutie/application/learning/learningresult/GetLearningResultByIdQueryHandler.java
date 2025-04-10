package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.query.GetLearningResultByIdQuery;
import com.edutie.application.learning.learningresult.view.LearningResultView;
import validation.WrapperResult;

public interface GetLearningResultByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningResultView<?>>, GetLearningResultByIdQuery> {
}

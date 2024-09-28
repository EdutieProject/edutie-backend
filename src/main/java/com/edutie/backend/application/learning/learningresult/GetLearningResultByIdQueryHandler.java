package com.edutie.backend.application.learning.learningresult;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

public interface GetLearningResultByIdQueryHandler extends UseCaseHandler<WrapperResult<LearningResult>, GetLearningResultByIdQuery> {
}

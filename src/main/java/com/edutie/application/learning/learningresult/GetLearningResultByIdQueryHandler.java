package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.query.GetLearningResultByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import validation.WrapperResult;

public interface GetLearningResultByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningResult<?>>, GetLearningResultByIdQuery> {
}

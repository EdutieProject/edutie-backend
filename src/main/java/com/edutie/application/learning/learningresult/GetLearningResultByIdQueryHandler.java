package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.query.GetLearningResultByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface GetLearningResultByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningExperience<?>>, GetLearningResultByIdQuery> {
}

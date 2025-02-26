package com.edutie.application.learning.learningresource;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface GetLearningResourceByIdQueryHandler extends UseCaseHandler<WrapperResult<LearningExperience>, GetLearningResourceByIdQuery> { }

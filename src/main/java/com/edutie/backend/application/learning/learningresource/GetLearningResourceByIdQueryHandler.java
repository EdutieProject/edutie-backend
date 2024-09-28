package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

public interface GetLearningResourceByIdQueryHandler extends UseCaseHandler<WrapperResult<LearningResource>, GetLearningResourceByIdQuery> { }

package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

import java.util.List;

public interface GetLearningResourcesByDefinitionIdQueryHandler extends UseCaseHandler<WrapperResult<List<LearningResource>>, GetLearningResourcesByDefinitionIdQuery> { }

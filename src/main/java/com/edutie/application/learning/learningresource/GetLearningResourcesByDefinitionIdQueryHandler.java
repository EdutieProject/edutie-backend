package com.edutie.application.learning.learningresource;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

import java.util.List;

public interface GetLearningResourcesByDefinitionIdQueryHandler extends UseCaseHandler<WrapperResult<List<LearningExperience>>, GetLearningResourcesByDefinitionIdQuery> { }

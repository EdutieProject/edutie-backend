package com.edutie.backend.application.management.learningresourcedefinition;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.learningresourcedefinition.queries.CreatedLearningResourceDefinitionsQuery;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import validation.WrapperResult;

import java.util.List;

public interface CreatedLearningResourceDefinitionsQueryHandler extends UseCaseHandler<WrapperResult<List<StaticLearningResourceDefinition>>, CreatedLearningResourceDefinitionsQuery> { }

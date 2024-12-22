package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

public interface CreateDynamicLearningResourceCommandHandler
        extends UseCaseHandler<WrapperResult<LearningResource>, CreateDynamicLearningResourceCommand> {
}

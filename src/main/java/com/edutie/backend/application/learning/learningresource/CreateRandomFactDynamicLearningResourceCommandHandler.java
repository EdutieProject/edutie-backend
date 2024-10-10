package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

public interface CreateRandomFactDynamicLearningResourceCommandHandler
        extends UseCaseHandler<WrapperResult<LearningResource>, CreateRandomFactDynamicLearningResourceCommand> {
}

package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateSimilarLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

public interface CreateSimilarLearningResourceCommandHandler
        extends UseCaseHandler<WrapperResult<LearningResource>, CreateSimilarLearningResourceCommand> {
}

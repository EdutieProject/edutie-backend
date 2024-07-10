package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import validation.WrapperResult;

public interface CreateLearningResourceCommandHandler extends UseCaseHandler<WrapperResult<LearningResource>, CreateLearningResourceCommand> {
}

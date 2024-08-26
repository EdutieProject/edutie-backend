package com.edutie.backend.application.learning.learningresource;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresource.commands.AssessSolutionCommand;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

public interface AssessSolutionCommandHandler extends UseCaseHandler<WrapperResult<LearningResult>, AssessSolutionCommand> {
}

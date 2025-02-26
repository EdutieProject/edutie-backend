package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import validation.WrapperResult;

public interface AssessSolutionCommandHandler extends UseCaseHandler<WrapperResult<LearningResult>, AssessSolutionCommand> { }

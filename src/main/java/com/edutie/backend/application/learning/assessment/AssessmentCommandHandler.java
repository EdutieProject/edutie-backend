package com.edutie.backend.application.learning.assessment;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.assessment.commands.AssessmentCommand;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

public interface AssessmentCommandHandler extends UseCaseHandler<WrapperResult<LearningResult>, AssessmentCommand> {
}

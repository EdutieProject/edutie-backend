package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.command.CreateBlankLearningSubjectCommand;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

public interface CreateBlankLearningSubjectCommandHandler extends UseCaseHandler<WrapperResult<LearningSubject>, CreateBlankLearningSubjectCommand> {
}

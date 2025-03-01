package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.command.RemoveLearningSubjectRequirementCommand;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

public interface RemoveLearningSubjectRequirementCommandHandler extends UseCaseHandler<WrapperResult<LearningSubject>, RemoveLearningSubjectRequirementCommand> {
}

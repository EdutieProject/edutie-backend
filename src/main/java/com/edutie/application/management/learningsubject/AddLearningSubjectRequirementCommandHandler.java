package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.command.AddLearningSubjectRequirementCommand;
import com.edutie.application.management.learningsubject.command.CreateBlankLearningSubjectCommand;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

public interface AddLearningSubjectRequirementCommandHandler extends UseCaseHandler<WrapperResult<LearningSubject>, AddLearningSubjectRequirementCommand> {
}

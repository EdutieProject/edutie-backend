package com.edutie.application.learning.learningexperience;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningexperience.command.CreateLearningExperienceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface CreateLearningExperienceCommandHandler
        extends UseCaseHandler<WrapperResult<LearningExperience<?>>, CreateLearningExperienceCommand> {
}

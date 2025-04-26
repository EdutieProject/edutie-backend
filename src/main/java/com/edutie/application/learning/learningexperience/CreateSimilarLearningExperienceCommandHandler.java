package com.edutie.application.learning.learningexperience;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningexperience.command.CreateSimilarLearningExperienceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface CreateSimilarLearningExperienceCommandHandler
        extends UseCaseHandler<WrapperResult<LearningExperience<?>>, CreateSimilarLearningExperienceCommand> {
}

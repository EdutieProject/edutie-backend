package com.edutie.application.learning.learningresource;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface CreateLearningResourceCommandHandler extends UseCaseHandler<WrapperResult<LearningExperience>, CreateLearningResourceCommand> { }

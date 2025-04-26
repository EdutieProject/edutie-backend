package com.edutie.application.learning.learningexperience;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningexperience.query.GetLearningExperienceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import validation.WrapperResult;

public interface GetLearningExperienceByIdQueryHandler
        extends UseCaseHandler<WrapperResult<LearningExperience<?>>, GetLearningExperienceByIdQuery> {
}

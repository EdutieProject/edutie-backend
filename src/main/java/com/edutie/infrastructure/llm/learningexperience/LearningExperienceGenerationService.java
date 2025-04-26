package com.edutie.infrastructure.llm.learningexperience;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;
import validation.WrapperResult;

public interface LearningExperienceGenerationService {
    WrapperResult<LearningExperience<?>> generate(LearningExperienceGenerationSchema schema);
}

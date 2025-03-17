package com.edutie.infrastructure.llm.learningexperience.implementation.dto;

import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;

public class SimpleProblemActivityLearningExperienceDto implements ExternalServiceDto<SimpleProblemActivityLearningExperience, LearningExperienceGenerationSchema> {


    @Override
    public SimpleProblemActivityLearningExperience intoDomainEntity(LearningExperienceGenerationSchema learningExperienceGenerationSchema) {
        //TODO
        return null;
    }
}

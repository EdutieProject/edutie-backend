package com.edutie.infrastructure.llm.learningexperience.implementation;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.infrastructure.llm.learningexperience.LearningExperienceGenerationService;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningExperienceGenerationServiceImplementation implements LearningExperienceGenerationService {
    @Override
    public WrapperResult<LearningExperience<?>> generate(LearningExperienceGenerationSchema schema) {
        log.info("Generating learning experience using LLM service using schema: {}", schema);
        //TODO: generation conditional ??? but first do it for simple problem activity
        return WrapperResult.successWrapper(SimpleProblemActivityLearningExperience.create());
    }
}

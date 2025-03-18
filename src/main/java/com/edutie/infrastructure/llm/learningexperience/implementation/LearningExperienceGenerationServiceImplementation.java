package com.edutie.infrastructure.llm.learningexperience.implementation;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.llm.learningexperience.LearningExperienceGenerationService;
import com.edutie.infrastructure.llm.learningexperience.implementation.dto.SimpleProblemActivityLearningExperienceDto;
import com.edutie.infrastructure.llm.learningexperience.schema.LearningExperienceGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningExperienceGenerationServiceImplementation extends ExternalService implements LearningExperienceGenerationService {
    @Value("${llm-service-url}")
    private String LLM_SERVICE_URL;

    @Override
    public WrapperResult<LearningExperience<?>> generate(LearningExperienceGenerationSchema schema) {
        log.info("Generating learning experience using LLM service using schema: {}", schema);
        //TODO: generation conditional ??? but first do it for simple problem activity
        final String simpleActivityLearningExperiencePath = LLM_SERVICE_URL + "/learning-experience/simple-problem";
        LearningExperience<?> learningExperience = new ExternalInfrastructureHandler<
                SimpleProblemActivityLearningExperience, LearningExperienceGenerationSchema, SimpleProblemActivityLearningExperienceDto
                >(this.getClass())
                .setActionUrl(simpleActivityLearningExperiencePath)
                .handle(schema, SimpleProblemActivityLearningExperienceDto.class).getValue();
        return WrapperResult.successWrapper(learningExperience);
    }
}

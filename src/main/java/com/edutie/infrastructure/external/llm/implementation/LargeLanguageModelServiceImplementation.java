package com.edutie.infrastructure.external.llm.implementation;

import com.edutie.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.infrastructure.external.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.external.llm.LargeLanguageModelService;
import com.edutie.infrastructure.external.llm.dto.learningresource.LearningResourceCreationDto;
import com.edutie.infrastructure.external.llm.dto.learningresult.LearningResultCreationDto;
import com.edutie.infrastructure.external.llm.dto.randomfact.RandomFactDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@Slf4j
public class LargeLanguageModelServiceImplementation implements LargeLanguageModelService {

    @Value("${llm-service-url}")
    private String LLM_SERVICE_URL;

    @Override
    public WrapperResult<LearningExperience> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        final String LEARNING_RESOURCE_LLM_URL = LLM_SERVICE_URL + "/learning-resource";
        return new ExternalInfrastructureHandler<LearningExperience, LearningResourceGenerationSchema, LearningResourceCreationDto>(this.getClass())
                .setActionUrl(LEARNING_RESOURCE_LLM_URL)
                .handle(learningResourceGenerationSchema, LearningResourceCreationDto.class);
    }

    @Override
    public WrapperResult<LearningResult> generateLearningResult(AssessmentSchema assessmentSchema) {
        final String LEARNING_RESULT_LLM_URL = LLM_SERVICE_URL + "/learning-result";
        return new ExternalInfrastructureHandler<LearningResult, AssessmentSchema, LearningResultCreationDto>(this.getClass())
                .setActionUrl(LEARNING_RESULT_LLM_URL)
                .handle(assessmentSchema, LearningResultCreationDto.class);
    }

    @Override
    public WrapperResult<RandomFact> generateRandomFact(RandomFactGenerationSchema randomFactGenerationSchema) {
        final String RANDOM_FACT_LLM_URL = LLM_SERVICE_URL + "/random-fact";
        return new ExternalInfrastructureHandler<RandomFact, RandomFactGenerationSchema, RandomFactDto>(this.getClass())
                .setActionUrl(RANDOM_FACT_LLM_URL)
                .disableSerializationFeatures(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .handle(randomFactGenerationSchema, RandomFactDto.class);
    }
}

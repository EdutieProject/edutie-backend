package com.edutie.backend.infrastructure.external.llm.implementation;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.infrastructure.external.common.ExternalInfrastructureHandler;
import com.edutie.backend.infrastructure.external.llm.LargeLanguageModelService;
import com.edutie.backend.infrastructure.external.llm.dto.learningresource.LearningResourceCreationDto;
import com.edutie.backend.infrastructure.external.llm.dto.learningresult.LearningResultCreationDto;
import com.edutie.backend.infrastructure.external.llm.dto.randomfact.RandomFactDto;
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
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        final String LEARNING_RESOURCE_LLM_URL = LLM_SERVICE_URL + "/learning-resource";
        return new ExternalInfrastructureHandler<LearningResource, LearningResourceGenerationSchema, LearningResourceCreationDto>(this.getClass())
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

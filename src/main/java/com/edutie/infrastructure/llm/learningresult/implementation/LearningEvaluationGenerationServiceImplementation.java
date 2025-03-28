package com.edutie.infrastructure.llm.learningresult.implementation;

import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.llm.learningresult.LearningEvaluationGenerationService;
import com.edutie.infrastructure.llm.learningresult.implementation.dto.LearningEvaluationDto;
import com.edutie.infrastructure.llm.learningresult.schema.LearningEvaluationGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class LearningEvaluationGenerationServiceImplementation extends ExternalService implements LearningEvaluationGenerationService {
    @Value("${llm-service-url}")
    private String LLM_SERVICE_URL;

    @Override
    public WrapperResult<LearningEvaluation> generate(LearningEvaluationGenerationSchema<?> schema) {
        final String learningEvaluationUrl = LLM_SERVICE_URL + "/learning-result/learning-evaluation";
        return new ExternalInfrastructureHandler<LearningEvaluation, LearningEvaluationGenerationSchema<?>, LearningEvaluationDto>(this.getClass())
                .setActionUrl(learningEvaluationUrl)
                .handle(schema, LearningEvaluationDto.class);
    }
}

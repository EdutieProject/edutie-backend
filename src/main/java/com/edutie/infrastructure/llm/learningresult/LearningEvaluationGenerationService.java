package com.edutie.infrastructure.llm.learningresult;

import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.infrastructure.llm.learningresult.schema.LearningEvaluationGenerationSchema;
import validation.WrapperResult;

public interface LearningEvaluationGenerationService {
    WrapperResult<LearningEvaluation> generate(LearningEvaluationGenerationSchema<?> schema);
}

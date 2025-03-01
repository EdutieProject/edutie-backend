package com.edutie.infrastructure.external.llm;

import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.infrastructure.external.common.ExternalService;
import validation.WrapperResult;

/**
 * Service for interaction with externally implemented LLM.
 */
public interface LargeLanguageModelService extends ExternalService {
    /**
     * Generates a learning resource based on the provided schema
     *
     * @param learningResourceGenerationSchema learning resource generation schema
     * @return Learning Resource Wrapper Result
     */
    WrapperResult<LearningExperience> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema);

    /**
     * Generates a learning Result based on the provided schema
     *
     * @param assessmentSchema assessment schema so-called Learning Result Generation Schema
     * @return Wrapper Result of Learning Result
     */
    WrapperResult<LearningResult> generateLearningResult(AssessmentSchema assessmentSchema);

    /**
     * Generates a random fact on the provided schema
     *
     * @param randomFactGenerationSchema random fact generation schema
     * @return Wrapper Result of Random Fact
     */
    WrapperResult<RandomFact> generateRandomFact(RandomFactGenerationSchema randomFactGenerationSchema);
}

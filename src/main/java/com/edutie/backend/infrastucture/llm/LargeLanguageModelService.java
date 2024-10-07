package com.edutie.backend.infrastucture.llm;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

/**
 * Service for interaction with externally implemented LLM.
 */
public interface LargeLanguageModelService {
    /**
     * Generates a learning resource based on the provided schema
     *
     * @param learningResourceGenerationSchema learning resource generation schema
     * @return Learning Resource Wrapper Result
     */
    WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema);

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

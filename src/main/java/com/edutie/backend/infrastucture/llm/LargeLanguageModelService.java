package com.edutie.backend.infrastucture.llm;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import validation.WrapperResult;

public interface LargeLanguageModelService {
    WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema);
}

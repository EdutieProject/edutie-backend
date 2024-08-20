package com.edutie.backend.infrastucture.llm;

import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

public interface LargeLanguageModelService {
    WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema);
    WrapperResult<LearningResult> assessStudentsSolution(AssessmentSchema assessmentSchema);
}

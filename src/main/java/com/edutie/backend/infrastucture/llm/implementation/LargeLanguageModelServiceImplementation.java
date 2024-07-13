package com.edutie.backend.infrastucture.llm.implementation;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Component
public class LargeLanguageModelServiceImplementation implements LargeLanguageModelService {
    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        LearningResource learningResource = LearningResource.create(
                learningResourceGenerationSchema.getStudentId(),
                learningResourceGenerationSchema.getLearningResourceDefinition().getId(),
                learningResourceGenerationSchema.getLearningResourceDefinition().getExerciseDescription().text(),
                Set.of(Hint.create("Hello!"), Hint.create("World!")),
                learningResourceGenerationSchema.getLearningResourceDefinition().getTheoryDescription().text(),
                learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription().text()
        );
        return WrapperResult.successWrapper(learningResource);
    }
}

package com.edutie.backend.mocks;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.ProblemDetail;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class LearningMocks {
    public static KnowledgeMapService knowledgeMapServiceMock() {
        return knowledgeSubjectId -> WrapperResult.successWrapper(List.of(
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")), 2),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("4e92752a-5ef8-420e-ba45-260b6b7af5fe")), 4),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("201b3e63-5340-4a35-8f51-8de8275dae1e")), 7),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("7ad5fd80-6337-4b69-8048-8a97e39aa963")), 8)
        ));
    }

    public static LargeLanguageModelService largeLanguageModelServiceMock() {
        return learningResourceGenerationSchema -> {
            LearningResource learningResource = LearningResource.create(
                    learningResourceGenerationSchema,
                    learningResourceGenerationSchema.getLearningResourceDefinition().getExerciseDescription().text(),
                    Set.of(Hint.create("Hello!"), Hint.create("World!")),
                    learningResourceGenerationSchema.getLearningResourceDefinition().getTheoryDescription() != null ? learningResourceGenerationSchema.getLearningResourceDefinition().getTheoryDescription().text() : null,
                    learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription() != null ? learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription().text() : null,
                    learningResourceGenerationSchema.getLearningResourceDefinition().getLearningRequirements().stream().map(o -> ProblemDetail.create(o.getId(), 1)).collect(Collectors.toSet())
            );
            return WrapperResult.successWrapper(learningResource);
        };
    }
}

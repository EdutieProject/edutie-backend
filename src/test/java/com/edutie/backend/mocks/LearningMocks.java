package com.edutie.backend.mocks;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.ProblemDetail;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
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
        return new LargeLanguageModelService() {
            @Override
            public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
                LearningResource learningResource = LearningResource.create(
                        learningResourceGenerationSchema,
                        Activity.create(learningResourceGenerationSchema.getLearningResourceDefinition().getExerciseDescription().text(), Set.of(Hint.create("Hello!"), Hint.create("World!"))),
                        Theory.create(learningResourceGenerationSchema.getLearningResourceDefinition().getTheoryDescription().text(), learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription() != null ? learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription().text() : null),
                        learningResourceGenerationSchema.getLearningResourceDefinition().getLearningRequirements().stream().map(o -> ProblemDetail.create(o.getId(), 1)).collect(Collectors.toSet())
                );
                return WrapperResult.successWrapper(learningResource);
            }


            @Override
            public WrapperResult<LearningResult> assessStudentsWork(AssessmentSchema assessmentSchema) {
                LearningResult learningResult = LearningResult.create(
                        assessmentSchema.getStudent(),
                        assessmentSchema.getSolutionSubmission(),
                        new Feedback("Great!", FeedbackType.POSITIVE)
                );
                learningResult.addAssessment(Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE));
                return WrapperResult.successWrapper(learningResult);
            }
        };
    }
}

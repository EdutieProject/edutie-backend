package com.edutie.backend.mocks;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import validation.WrapperResult;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExternalServiceMocks {
    public static KnowledgeMapService knowledgeMapServiceMock() {
        return knowledgeSubjectIds -> WrapperResult.successWrapper(Set.of(
                new KnowledgeCorrelation(knowledgeSubjectIds.stream().findFirst().get(), new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")), 2),
                new KnowledgeCorrelation(knowledgeSubjectIds.stream().findFirst().get(), new KnowledgeSubjectId(UUID.fromString("4e92752a-5ef8-420e-ba45-260b6b7af5fe")), 4),
                new KnowledgeCorrelation(knowledgeSubjectIds.stream().findFirst().get(), new KnowledgeSubjectId(UUID.fromString("201b3e63-5340-4a35-8f51-8de8275dae1e")), 7),
                new KnowledgeCorrelation(knowledgeSubjectIds.stream().findFirst().get(), new KnowledgeSubjectId(UUID.fromString("7ad5fd80-6337-4b69-8048-8a97e39aa963")), 8)
        ));
    }

    public static LargeLanguageModelService largeLanguageModelServiceMock() {
        return new LargeLanguageModelService() {
            @Override
            public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
                LearningResource learningResource = LearningResource.create(
                        learningResourceGenerationSchema,
                        Activity.create("Hello there it is activity text here!", Set.of(Hint.create("Hello!"), Hint.create("World!"))),
                        Theory.create("The general idea is simple...", "graph TD [more graph below]")
                );
                return WrapperResult.successWrapper(learningResource);
            }


            @Override
            public WrapperResult<LearningResult> generateLearningResult(AssessmentSchema assessmentSchema) {
                Set<LearningRequirementId> learningRequirementIds = assessmentSchema.getQualifiedRequirements().stream().map(o -> o.getLearningRequirement().getId()).collect(Collectors.toSet());
                LearningResult learningResult = LearningResult.create(
                        assessmentSchema.getStudent(),
                        assessmentSchema.getSolutionSubmission(),
                        new Feedback("Great!", FeedbackType.POSITIVE),
                        learningRequirementIds.stream().map(
                                o -> Assessment.create(o, new Grade((int) (Math.random() * 6)),
                                        "Thats a feedback for a student",
                                        assessmentSchema.getQualifiedRequirements().stream().filter(x -> x.getLearningRequirement().getId().equals(o)).toList())
                        ).collect(Collectors.toSet())
                );
                return WrapperResult.successWrapper(learningResult);
            }

            /**
             * Generates a random fact on the provided schema
             *
             * @param randomFactGenerationSchema random fact generation schema
             * @return Wrapper Result of Random Fact
             */
            @Override
            public WrapperResult<RandomFact> generateRandomFact(RandomFactGenerationSchema randomFactGenerationSchema) {
                return WrapperResult.successWrapper(new RandomFact("Mount Everest is the highest mountain in the world"));
            }
        };
    }
}

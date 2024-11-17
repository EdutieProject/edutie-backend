package com.edutie.backend.mocks;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastructure.external.llm.LargeLanguageModelService;
import validation.WrapperResult;

import java.util.Set;
import java.util.stream.Collectors;

public class ExternalServiceMocks {
    public static KnowledgeMapService knowledgeMapServiceMock() {
        return new KnowledgeMapService() {
            @Override
            public WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(Set<LearningRequirement> sourceRequirements, Set<LearningRequirement> comparedLearningRequirements) {
                return WrapperResult.successWrapper(
                        sourceRequirements.stream().flatMap(
                                o -> comparedLearningRequirements.stream().map(
                                        compared -> new LearningRequirementCorrelation(o.getId(), compared.getId(), (int) Math.floor(Math.random() * 100))
                                )
                        ).collect(Collectors.toSet()));
            }

            @Override
            public WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
                return WrapperResult.successWrapper(KnowledgeSubject.create(
                        new KnowledgeSubjectId(),
                        "Mount everest",
                        "A big ass mountain"
                ));
            }
        };
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
                        assessmentSchema.getSolutionSubmission(),
                        new Feedback("Great!"),
                        learningRequirementIds.stream().map(
                                o -> Assessment.create(o, new Grade((int) (Math.random() * 6)),
                                        Feedback.of("Thats a feedback for a student"),
                                        assessmentSchema.getQualifiedRequirements().stream().filter(x -> x.getLearningRequirement().getId().equals(o)).toList())
                        ).collect(Collectors.toSet())
                );
                return WrapperResult.successWrapper(learningResult);
            }

            @Override
            public WrapperResult<RandomFact> generateRandomFact(RandomFactGenerationSchema randomFactGenerationSchema) {
                return WrapperResult.successWrapper(new RandomFact("Mount Everest is the highest mountain in the world"));
            }
        };
    }
}

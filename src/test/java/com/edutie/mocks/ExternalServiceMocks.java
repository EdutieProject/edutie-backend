package com.edutie.mocks;

import com.edutie.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.infrastructure.external.llm.LargeLanguageModelService;
import validation.WrapperResult;

import java.util.Set;
import java.util.stream.Collectors;

public class ExternalServiceMocks {
    public static KnowledgeMapService knowledgeMapServiceMock() {
        return new KnowledgeMapService() {
            @Override
            public WrapperResult<Set<LearningRequirementCorrelation>> getLearningRequirementCorrelations(Set<LearningSubject> sourceRequirements, Set<LearningSubject> comparedLearningSubjects) {
                return WrapperResult.successWrapper(
                        sourceRequirements.stream().flatMap(
                                o -> comparedLearningSubjects.stream().map(
                                        compared -> new LearningRequirementCorrelation(o.getId(), compared.getId(), (int) Math.floor(Math.random() * 100))
                                )
                        ).collect(Collectors.toSet()));
            }

            @Override
            public WrapperResult<KnowledgeSubject> getMostCorrelatedKnowledgeSubject(KnowledgeSubjectId knowledgeSubjectId) {
                return WrapperResult.successWrapper(KnowledgeSubject.create(
                        new KnowledgeSubjectId(),
                        "Mount everest"
                ));
            }
        };
    }

    public static LargeLanguageModelService largeLanguageModelServiceMock() {
        return new LargeLanguageModelService() {
            @Override
            public WrapperResult<LearningExperience> generateLearningResource(LearningResourceGenerationSchema schema) {
                LearningExperience learningExperience = LearningExperience.create(
                        schema.getStudentMetadata(),
                        schema.getLearningResourceDefinition(),
                        schema.getQualifiedRequirements(),
                        ActivityBase.create("Hello this is sample activity", Set.of(Hint.create("Only one hint"))),
                        Set.of(TheoryCard.create(new LearningRequirementId(), "One theory card for now")),
                        new Visualisation("")
                );
                return WrapperResult.successWrapper(learningExperience);
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

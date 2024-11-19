package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class LearningResultPersistenceTests {
    @Autowired
    private MockUser mockUser;
    @Autowired
    private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private LearningResourcePersistence learningResourcePersistence;

    private LearningRequirement learningRequirement;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        learningRequirement = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
    }

    private LearningResourceDefinition createAndSaveLearningResourceDefinition() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(learningRequirement) // Knowledge subject is used for testing
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        return learningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(LearningResourceDefinition learningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                LearningResourceGenerationSchema.create(mockUser.getStudentProfile(), learningResultPersistence, learningResourceDefinition, Set.of()),
                "graph TD",
                Activity.create("", Set.of()),
                learningResourceDefinition.getLearningRequirements().stream().map(o -> TheoryCard.create(o.getId(), "Something")).collect(Collectors.toSet())
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getLatestResultsOfStudentSingleTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), null, "Text of the report", 0),
                new Feedback(""),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLatestResultsOfStudent(
                mockUser.getStudentProfile().getId(), 2,
                LocalDateTime.of(2004, 10,10, 10, 10)
        );

        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertFalse(learningResultsWrapper.getValue().isEmpty());
        Assertions.assertTrue(learningResultsWrapper.getValue().contains(learningResult));
    }


    @Test
    public void getLatestResultsOfStudentEmptyTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), null, "Text of the report", 0),
                new Feedback(""),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult);

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLatestResultsOfStudent(
                mockUser.getStudentProfile().getId(), 2,
                LocalDateTime.now().plusDays(1)) ;

        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertFalse(learningResultsWrapper.getValue().contains(learningResult));
    }

    @Test
    public void getLearningResultsOfStudentByLearningResourceDefinitionIdSingleTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource sampleLearningResource = createAndSaveLearningResource(learningResourceDefinition);

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByLearningResourceDefinitionId(
                mockUser.getStudentProfile().getId(), sampleLearningResource.getDefinitionId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertFalse(learningResultsWrapper.getValue().isEmpty());
        Assertions.assertTrue(learningResultsWrapper.getValue().contains(learningResult));
     }

    @Test
    public void getLearningResultsOfStudentByLearningResourceDefinitionIdEmptyTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource sampleLearningResource = createAndSaveLearningResource(learningResourceDefinition);

        learningResourcePersistence.save(sampleLearningResource).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByLearningResourceDefinitionId(
                mockUser.getStudentProfile().getId(), new LearningResourceDefinitionId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertTrue(learningResultsWrapper.getValue().isEmpty());
    }

    @Test
    public void getLearningResultsOfStudentByKnowledgeSubjectIdSingleTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource sampleLearningResource = createAndSaveLearningResource(learningResourceDefinition);

        learningResourcePersistence.save(sampleLearningResource).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByKnowledgeSubjectId(
                mockUser.getStudentProfile().getId(), learningRequirement.getKnowledgeSubjectId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertTrue(learningResultsWrapper.getValue().contains(learningResult));
    }

    @Test
    public void getLearningResultsOfStudentByKnowledgeSubjectIdEmptyTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource sampleLearningResource = createAndSaveLearningResource(learningResourceDefinition);

        learningResourcePersistence.save(sampleLearningResource).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByKnowledgeSubjectId(
                mockUser.getStudentProfile().getId(), new KnowledgeSubjectId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertTrue(learningResultsWrapper.getValue().isEmpty());
    }
}

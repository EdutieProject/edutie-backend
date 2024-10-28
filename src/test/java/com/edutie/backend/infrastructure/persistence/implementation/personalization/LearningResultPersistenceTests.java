package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.mocks.LearningResourceMocks;
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

@SpringBootTest
public class LearningResultPersistenceTests {
    @Autowired
    private MockUser mockUser;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private LearningResourcePersistence learningResourcePersistence;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
    }

    @Test
    public void getLatestResultsOfStudentSingleTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), null, "Text of the report", 0),
                new Feedback("", FeedbackType.NEUTRAL),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, "", List.of()))
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
                new Feedback("", FeedbackType.NEUTRAL),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, "", List.of()))
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
        LearningResource sampleLearningResource = LearningResourceMocks.sampleLearningResource(
                mockUser.getStudentProfile(),
                learningResultPersistence,
                mockUser.getEducatorProfile()
        );
        learningResourcePersistence.save(sampleLearningResource).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback", FeedbackType.POSITIVE),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, "", List.of()))
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
        LearningResource sampleLearningResource = LearningResourceMocks.sampleLearningResource(
                mockUser.getStudentProfile(),
                learningResultPersistence,
                mockUser.getEducatorProfile()
        );
        learningResourcePersistence.save(sampleLearningResource).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningResource, "My report", 0),
                new Feedback("Feedback", FeedbackType.POSITIVE),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, "", List.of()))
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

    }

    @Test
    public void getLearningResultsOfStudentByKnowledgeSubjectIdEmptyTest() {

    }
}

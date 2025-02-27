package com.edutie.infrastructure.persistence.implementation.personalization;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.solutionsubmission.SolutionSubmission;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.MockUser;
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
    private LearningExperiencePersistence learningExperiencePersistence;

    private LearningSubject learningSubject;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        learningSubject = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
    }

    private StaticLearningResourceDefinition createAndSaveLearningResourceDefinition() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(learningSubject) // Knowledge subject is used for testing
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        return staticLearningResourceDefinition;
    }

    private LearningExperience createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningExperience learningExperience = LearningExperience.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                ActivityBase.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return learningExperience;
    }

    @Test
    public void getLatestResultsOfStudentSingleTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), null, DefinitionType.DYNAMIC, "Text of the report", 0),
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
                SolutionSubmission.create(mockUser.getStudentProfile(), null, DefinitionType.DYNAMIC, "Text of the report", 0),
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
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience sampleLearningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningExperience.getId(), DefinitionType.DYNAMIC, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByLearningResourceDefinitionId(
                mockUser.getStudentProfile().getId(), sampleLearningExperience.getDefinitionId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertFalse(learningResultsWrapper.getValue().isEmpty());
        Assertions.assertTrue(learningResultsWrapper.getValue().contains(learningResult));
     }

    @Test
    public void getLearningResultsOfStudentByLearningResourceDefinitionIdEmptyTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience sampleLearningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        learningExperiencePersistence.save(sampleLearningExperience).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningExperience.getId(), DefinitionType.DYNAMIC, "My report", 0),
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
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience sampleLearningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        learningExperiencePersistence.save(sampleLearningExperience).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningExperience.getId(), DefinitionType.DYNAMIC, "My report", 0),
                new Feedback("Feedback"),
                Set.of(Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of(""), List.of()))
        );
        learningResultPersistence.save(learningResult).throwIfFailure();

        WrapperResult<List<LearningResult>> learningResultsWrapper = learningResultPersistence.getLearningResultsOfStudentByKnowledgeSubjectId(
                mockUser.getStudentProfile().getId(), learningSubject.getKnowledgeSubjectId()
        );
        Assertions.assertTrue(learningResultsWrapper.isSuccess());
        Assertions.assertTrue(learningResultsWrapper.getValue().contains(learningResult));
    }

    @Test
    public void getLearningResultsOfStudentByKnowledgeSubjectIdEmptyTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience sampleLearningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        learningExperiencePersistence.save(sampleLearningExperience).throwIfFailure();

        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(mockUser.getStudentProfile(), sampleLearningExperience.getId(), DefinitionType.DYNAMIC, "My report", 0),
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

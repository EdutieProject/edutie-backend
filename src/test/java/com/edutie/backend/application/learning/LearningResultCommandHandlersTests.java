package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.backend.application.learning.learningresult.implementation.AssessSolutionCommandHandlerImplementation;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.domainservice.personalization.learningresult.implementation.LearningResultPersonalizationServiceImplementation;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.ExternalServiceMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

@SpringBootTest
public class LearningResultCommandHandlersTests {
    @Autowired
    private MockUser mockUser;
    // Persistence
    @Autowired
    StudentPersistence studentPersistence;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    LearningResourcePersistence learningResourcePersistence;
    @Autowired
    SolutionSubmissionPersistence solutionSubmissionPersistence;
    // Handlers
    @Autowired
    AssessSolutionCommandHandler assessSolutionCommandHandler;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();

        assessSolutionCommandHandler = new AssessSolutionCommandHandlerImplementation(
                learningResourcePersistence,
                studentPersistence,
                solutionSubmissionPersistence,
                learningResultPersistence,
                new LearningResultPersonalizationServiceImplementation(ExternalServiceMocks.largeLanguageModelServiceMock())
        );
    }

    private LearningResourceDefinition createAndSaveLearningResourceDefinition() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                // We test assessment count according to the count of learning reqs
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        return learningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(LearningResourceDefinition learningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                LearningResourceGenerationSchema.create(mockUser.getStudentProfile(), learningResultPersistence, Set.of(), learningResourceDefinition),
                Activity.create("", Set.of()),
                Theory.create("", "")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void assessSolutionTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(learningResourceDefinition);

        AssessSolutionCommand command = new AssessSolutionCommand()
                .learningResourceId(learningResource.getId())
                .studentUserId(mockUser.getUserId())
                .solutionSubmissionText("Hello world")
                .hintsRevealedCount(0);
        WrapperResult<LearningResult> learningResultWrapper = assessSolutionCommandHandler.handle(command);

        Assertions.assertTrue(learningResultWrapper.isSuccess());
        Assertions.assertFalse(learningResultWrapper.getValue().getAssessments().isEmpty());
    }
}

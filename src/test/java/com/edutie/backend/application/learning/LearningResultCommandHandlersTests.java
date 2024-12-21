package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.backend.application.learning.learningresult.implementation.AssessSolutionCommandHandlerImplementation;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
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
import java.util.stream.Collectors;

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
                new LearningResultPersonalizationServiceImplementation(
                        ExternalServiceMocks.largeLanguageModelServiceMock(),
                        learningResourcePersistence
                )
        );
    }

    private StaticLearningResourceDefinition createAndSaveLearningResourceDefinition() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                // We test assessment count according to the count of learning reqs
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        return staticLearningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                Activity.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void assessSolutionTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(staticLearningResourceDefinition);

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

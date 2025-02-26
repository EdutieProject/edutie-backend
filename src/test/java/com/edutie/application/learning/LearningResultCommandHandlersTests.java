package com.edutie.application.learning;

import com.edutie.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.application.learning.learningresult.implementation.AssessSolutionCommandHandlerImplementation;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.Activity;
import com.edutie.domain.core.learning.learningexperience.entities.Hint;
import com.edutie.domain.core.learning.learningexperience.entities.TheoryCard;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningResourcePersistence;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.domain.service.personalization.learningresult.implementation.LearningResultPersonalizationServiceImplementation;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.ExternalServiceMocks;
import com.edutie.mocks.MockUser;
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

    private LearningExperience createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningExperience learningExperience = LearningExperience.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                Activity.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningResourcePersistence.save(learningExperience).throwIfFailure();
        return learningExperience;
    }

    @Test
    public void assessSolutionTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience learningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        AssessSolutionCommand command = new AssessSolutionCommand()
                .learningResourceId(learningExperience.getId())
                .studentUserId(mockUser.getUserId())
                .solutionSubmissionText("Hello world")
                .hintsRevealedCount(0);
        WrapperResult<LearningResult> learningResultWrapper = assessSolutionCommandHandler.handle(command);

        Assertions.assertTrue(learningResultWrapper.isSuccess());
        Assertions.assertFalse(learningResultWrapper.getValue().getAssessments().isEmpty());
    }
}

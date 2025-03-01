package com.edutie.application.learning;

import com.edutie.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.CreateDynamicLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.CreateSimilarLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.application.learning.learningresource.commands.CreateSimilarLearningResourceCommand;
import com.edutie.application.learning.learningresource.implementation.CreateLearningResourceCommandHandlerImplementation;
import com.edutie.application.learning.learningresource.implementation.CreateDynamicLearningResourceCommandHandlerImplementation;
import com.edutie.application.learning.learningresource.implementation.CreateSimilarLearningResourceCommandHandlerImplementation;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.personalization.strategy.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.domain.service.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.domain.service.personalization.learningresource.implementation.LearningResourcePersonalizationServiceImplementation;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.ExternalServiceMocks;
import com.edutie.mocks.LearningResourceMocks;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

@SpringBootTest
public class LearningExperienceCommandHandlersTests {
    @Autowired
    private MockUser mockUser;
    // Persistence
    @Autowired
    StudentPersistence studentPersistence;
    @Autowired
    LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    LearningExperiencePersistence learningExperiencePersistence;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    // Service
    @Autowired
    LearningResourcePersonalizationService learningResourcePersonalizationService;
    @Autowired
    PersonalizationRuleSelectionEngine personalizationRuleSelectionEngine;
    // Handlers
    @Autowired
    CreateLearningResourceCommandHandler createLearningResourceCommandHandler;
    @Autowired
    CreateDynamicLearningResourceCommandHandler createDynamicLearningResourceCommandHandler;
    @Autowired
    CreateSimilarLearningResourceCommandHandler createSimilarLearningResourceCommandHandler;


    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        // save the learning req for mocking purpose
        LearningSubject learningSubject = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        // Remove mocking out the external services to integration test
        learningResourcePersonalizationService = new LearningResourcePersonalizationServiceImplementation(
                learningResultPersistence,
                ExternalServiceMocks.largeLanguageModelServiceMock(),
                personalizationRuleSelectionEngine
        );
        createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(
                studentPersistence,
                learningResourceDefinitionPersistence,
                learningExperiencePersistence,
                learningResourcePersonalizationService
        );
        createDynamicLearningResourceCommandHandler = new CreateDynamicLearningResourceCommandHandlerImplementation(
                studentPersistence,
                (Student student) -> WrapperResult.successWrapper(Set.of(learningSubject)),
                learningResourcePersonalizationService,
                learningExperiencePersistence
        );
        createSimilarLearningResourceCommandHandler = new CreateSimilarLearningResourceCommandHandlerImplementation(
                learningExperiencePersistence,
                learningResourceDefinitionPersistence,
                studentPersistence,
                learningResourcePersonalizationService
        );
    }


    @Test
    public void createLearningResourceForEmptyLearningHistory() {
        LearningSubject learningSubject = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.of("Theory description from definition"),
                PromptFragment.of("Exercise description from definition"),
                Set.of(learningSubject)
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();

        CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(staticLearningResourceDefinition.getId()).studentUserId(mockUser.getUserId());
        WrapperResult<LearningExperience> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();

        Assertions.assertTrue(learningResourceWrapperResult.isSuccess());
        Assertions.assertFalse(learningResourceWrapperResult.getValue().getRequirements().isEmpty());
    }

    @Test
    public void createLearningResourceWithLearningHistory() {
        createLearningResourceForEmptyLearningHistory(); // create learning result for student

        // Create a resource definition
        LearningSubject relatedRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
        learningSubjectPersistence.save(relatedRequirement);
        StaticLearningResourceDefinition definition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.of("Theory DESC!"),
                PromptFragment.of("Exercise DESC!"),
                Set.of(relatedRequirement)
        );
        learningResourceDefinitionPersistence.save(definition).throwIfFailure();


        CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(definition.getId()).studentUserId(mockUser.getUserId());
        WrapperResult<LearningExperience> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command);

        Assertions.assertTrue(learningResourceWrapperResult.isSuccess());
    }

    @Test
    public void createRandomFactDynamicLearningResourceTest() {
        CreateDynamicLearningResourceCommand command = new CreateDynamicLearningResourceCommand()
                .studentUserId(mockUser.getUserId())
                .contextText("A tortoise can weigh as much as 100 kilogrammes");

        WrapperResult<LearningExperience> learningResourceWrapper = createDynamicLearningResourceCommandHandler.handle(command).throwIfFailure();

        Assertions.assertTrue(learningResourceWrapper.isSuccess());
        Assertions.assertEquals(DefinitionType.DYNAMIC, learningResourceWrapper.getValue().getDefinitionType());
        Assertions.assertFalse(learningResourceWrapper.getValue().getRequirements().isEmpty());
    }

    @Test
    public void createSimilarLearningResourceTest() {
        StaticLearningResourceDefinition learningResourceDefinition = LearningResourceMocks.sampleLearningResourceDefinition(mockUser.getEducatorProfile());
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        LearningExperience learningExperience = LearningExperience.create(
                mockUser.getStudentProfile(),
                learningResourceDefinition,
                Set.of(),
                ActivityBase.create("Hello", Set.of()),
                Set.of(),
                new Visualisation("graph TD")
        );
        learningExperiencePersistence.save(learningExperience).throwIfFailure();

        CreateSimilarLearningResourceCommand command = new CreateSimilarLearningResourceCommand()
                .studentUserId(mockUser.getUserId())
                .learningExperienceId(learningExperience.getId());

        WrapperResult<LearningExperience> learningResourceWrapper = createSimilarLearningResourceCommandHandler.handle(command);

        Assertions.assertTrue(learningResourceWrapper.isSuccess());
        Assertions.assertEquals(learningResourceDefinition.getId(), learningResourceWrapper.getValue().getDefinitionId());
    }
}

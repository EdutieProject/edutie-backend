package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.CreateDynamicLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.implementation.CreateLearningResourceCommandHandlerImplementation;
import com.edutie.backend.application.learning.learningresource.implementation.CreateDynamicLearningResourceCommandHandlerImplementation;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.strategy.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresource.implementation.LearningResourcePersonalizationServiceImplementation;
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
public class LearningResourceCommandHandlersTests {
    @Autowired
    private MockUser mockUser;
    // Persistence
    @Autowired
    StudentPersistence studentPersistence;
    @Autowired
    LearningRequirementPersistence learningRequirementPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    LearningResourcePersistence learningResourcePersistence;
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



    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        // Remove mocking out the external services to integration test
        learningResourcePersonalizationService = new LearningResourcePersonalizationServiceImplementation(
                learningResultPersistence,
                ExternalServiceMocks.largeLanguageModelServiceMock(),
                personalizationRuleSelectionEngine
        );
        createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(
                studentPersistence,
                learningResourceDefinitionPersistence,
                learningResourcePersistence,
                learningResourcePersonalizationService
        );
        // save the learning req for mocking purpose
        LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        createDynamicLearningResourceCommandHandler = new CreateDynamicLearningResourceCommandHandlerImplementation(
                studentPersistence,
                (Student student) -> WrapperResult.successWrapper(Set.of(learningRequirement)),
                learningResourcePersonalizationService,
                learningResourcePersistence
        );
    }


    @Test
    public void createLearningResourceForEmptyLearningHistory() {
        LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.of("Theory description from definition"),
                PromptFragment.of("Exercise description from definition"),
                Set.of(learningRequirement)
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();

        CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(staticLearningResourceDefinition.getId()).studentUserId(mockUser.getUserId());
        WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();

        Assertions.assertTrue(learningResourceWrapperResult.isSuccess());
        Assertions.assertFalse(learningResourceWrapperResult.getValue().getQualifiedRequirements().isEmpty());
    }

    @Test
    public void createLearningResourceWithLearningHistory() {
        createLearningResourceForEmptyLearningHistory(); // create learning result for student

        // Create a resource definition
        LearningRequirement relatedRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
        learningRequirementPersistence.save(relatedRequirement);
        StaticLearningResourceDefinition definition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                PromptFragment.of("Theory DESC!"),
                PromptFragment.of("Exercise DESC!"),
                Set.of(relatedRequirement)
        );
        learningResourceDefinitionPersistence.save(definition).throwIfFailure();


        CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(definition.getId()).studentUserId(mockUser.getUserId());
        WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command);

        Assertions.assertTrue(learningResourceWrapperResult.isSuccess());
    }

    @Test
    public void createRandomFactDynamicLearningResourceTest() {
        CreateDynamicLearningResourceCommand command = new CreateDynamicLearningResourceCommand()
                .studentUserId(mockUser.getUserId())
                .contextText("A tortoise can weigh as much as 100 kilogrammes");

        WrapperResult<LearningResource> learningResourceWrapper = createDynamicLearningResourceCommandHandler.handle(command).throwIfFailure();

        Assertions.assertTrue(learningResourceWrapper.isSuccess());
        Assertions.assertEquals(DefinitionType.DYNAMIC, learningResourceWrapper.getValue().getDefinitionType());
        Assertions.assertFalse(learningResourceWrapper.getValue().getQualifiedRequirements().isEmpty());
    }
}

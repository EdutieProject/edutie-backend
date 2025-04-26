package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.AddLearningSubjectRequirementCommandHandler;
import com.edutie.application.management.learningsubject.command.AddLearningSubjectRequirementCommand;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.education.learningsubject.service.StudentObjectiveInferringService;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddLearningSubjectRequirementCommandHandlerImplementationTest {
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;
    // for integration testing
    @Autowired
    private StudentObjectiveInferringService studentObjectiveInferringService;

    private AddLearningSubjectRequirementCommandHandler handler;
    private LearningSubject learningSubject;

    @Autowired
    MockUser mockUser;

    @BeforeEach
    public void setUp() {
        mockUser.saveToPersistence();
        learningSubject = LearningSubject.createBlank(mockUser.getEducatorProfile(), "Learning Subject");
        learningSubjectRepository.save(learningSubject);
    }

    @Test
    public void handleUnitSuccess() {
        KnowledgeOrigin knowledgeOrigin = new KnowledgeOrigin();
        knowledgeOrigin.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningSubject.setKnowledgeOrigin(knowledgeOrigin);
        learningSubjectRepository.save(learningSubject);

        handler = new AddLearningSubjectRequirementCommandHandlerImplementation(
                educatorPersistence,
                learningSubjectPersistence,
                (title, knowledgeOrigin1) -> WrapperResult.successWrapper(PromptFragment.of("Hello there."))
        );

        AddLearningSubjectRequirementCommand command = new AddLearningSubjectRequirementCommand()
                .educatorUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId())
                .requirementTitle("New requirement")
                .requirementOrdinal(0);

        WrapperResult<LearningSubject> result = handler.handle(command);

        System.out.println(result.getError());
        assertTrue(result.isSuccess());
        assertEquals("New requirement", result.getValue().getRequirements().getFirst().getTitle());
    }

    @Test
    public void handleUnitFailEmptyKnowledgeOrigin() {
        handler = new AddLearningSubjectRequirementCommandHandlerImplementation(
                educatorPersistence,
                learningSubjectPersistence,
                (title, knowledgeOrigin) -> WrapperResult.successWrapper(PromptFragment.of("Hello there."))
        );

        AddLearningSubjectRequirementCommand command = new AddLearningSubjectRequirementCommand()
                .educatorUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId())
                .requirementTitle("New requirement")
                .requirementOrdinal(0);

        WrapperResult<LearningSubject> result = handler.handle(command);

        System.out.println(result.getError());
        assertTrue(result.isFailure());
    }

    @Test
    public void handleIntegrationSuccess() {
        KnowledgeOrigin knowledgeOrigin = new KnowledgeOrigin();
        knowledgeOrigin.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningSubject.setKnowledgeOrigin(knowledgeOrigin);
        learningSubjectRepository.save(learningSubject);

        handler = new AddLearningSubjectRequirementCommandHandlerImplementation(
                educatorPersistence,
                learningSubjectPersistence,
                (title, knowledgeOrigin1) -> WrapperResult.successWrapper(PromptFragment.of("Hello there."))
        );

        AddLearningSubjectRequirementCommand command = new AddLearningSubjectRequirementCommand()
                .educatorUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId())
                .requirementTitle("New requirement")
                .requirementOrdinal(0);

        WrapperResult<LearningSubject> result = handler.handle(command);

        System.out.println(result.getError());
        assertTrue(result.isSuccess());
        assertEquals("New requirement", result.getValue().getRequirements().getFirst().getTitle());
        assertNotNull(result.getValue().getRequirements().getFirst().getStudentObjective().text());
    }

}
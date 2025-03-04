package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.AddLearningSubjectRequirementCommandHandler;
import com.edutie.application.management.learningsubject.SetLearningSubjectKnowledgeSubjectCommandHandler;
import com.edutie.application.management.learningsubject.command.AddLearningSubjectRequirementCommand;
import com.edutie.application.management.learningsubject.command.SetLearningSubjectKnowledgeSubjectCommand;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
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
import validation.OperationFailureException;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SetLearningSubjectKnowledgeSubjectCommandHandlerImplementationTest {
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;

    private SetLearningSubjectKnowledgeSubjectCommandHandler handler;
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
        final KnowledgeSubjectId knowledgeSubjectId = new KnowledgeSubjectId();

        handler = new SetLearningSubjectKnowledgeSubjectCommandHandlerImplementation(educatorPersistence, learningSubjectPersistence);

        SetLearningSubjectKnowledgeSubjectCommand command = new SetLearningSubjectKnowledgeSubjectCommand()
                .educatorUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId())
                .knowledgeSubjectId(knowledgeSubjectId);

        WrapperResult<LearningSubject> result = handler.handle(command);

        System.out.println(result.getError());
        assertTrue(result.isSuccess());
        assertEquals(knowledgeSubjectId, result.getValue().getKnowledgeOrigin().getKnowledgeSubjectId());
    }

    @Test
    public void handleUnitUnprivilegedEducatorFailure() {
        final KnowledgeSubjectId knowledgeSubjectId = new KnowledgeSubjectId();

        handler = new SetLearningSubjectKnowledgeSubjectCommandHandlerImplementation(educatorPersistence, learningSubjectPersistence);

        UserId unprivilegedEducatorUserId = new UserId();
        Educator educator = Educator.create(unprivilegedEducatorUserId);
        educatorPersistence.save(educator);

        SetLearningSubjectKnowledgeSubjectCommand command = new SetLearningSubjectKnowledgeSubjectCommand()
                .educatorUserId(unprivilegedEducatorUserId)
                .learningSubjectId(learningSubject.getId())
                .knowledgeSubjectId(knowledgeSubjectId);

        assertThrows(OperationFailureException.class, () -> handler.handle(command));
    }
}
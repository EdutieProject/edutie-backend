package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.RemoveLearningSubjectRequirementCommandHandler;
import com.edutie.application.management.learningsubject.command.RemoveLearningSubjectRequirementCommand;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
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
class RemoveLearningSubjectRequirementCommandHandlerImplementationTest {
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;

    private RemoveLearningSubjectRequirementCommandHandler handler;
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
        LearningSubjectRequirement elementalRequirement = LearningSubjectRequirement.create(learningSubject, "Hello", PromptFragment.of("World!"), 0);
        learningSubject.getRequirements().add(elementalRequirement);
        learningSubjectRepository.save(learningSubject);

        assertEquals(1, learningSubjectRepository.findById(learningSubject.getId()).get().getRequirements().size());

        handler = new RemoveLearningSubjectRequirementCommandHandlerImplementation(educatorPersistence, learningSubjectPersistence);

        RemoveLearningSubjectRequirementCommand command = new RemoveLearningSubjectRequirementCommand()
                .educatorUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId())
                .learningSubjectRequirementId(elementalRequirement.getId());

        WrapperResult<LearningSubject> result = handler.handle(command);

        System.out.println(result.getError());
        assertTrue(result.isSuccess());
        // check saved state
        assertEquals(0, learningSubjectRepository.findById(learningSubject.getId()).get().getRequirements().size());
        // check returned state
        assertEquals(0, result.getValue().getRequirements().size());
    }

    @Test
    public void handleUnitNotFoundFailure() {
        //TODO: test case when the information about removal success is necessary
        // e.g. when 404 info is necessary when provided command elemental req id is invalid
    }

    @Test
    public void handleUnitUnprivilegedEducatorFailure() {
        handler = new RemoveLearningSubjectRequirementCommandHandlerImplementation(educatorPersistence, learningSubjectPersistence);

        UserId unprivilegedEducatorUserId = new UserId();
        Educator educator = Educator.create(unprivilegedEducatorUserId);
        educatorPersistence.save(educator);

        RemoveLearningSubjectRequirementCommand command = new RemoveLearningSubjectRequirementCommand()
                .educatorUserId(unprivilegedEducatorUserId)
                .learningSubjectId(learningSubject.getId())
                .learningSubjectRequirementId(new ElementalRequirementId());

        assertThrows(OperationFailureException.class, () -> handler.handle(command));
    }
}
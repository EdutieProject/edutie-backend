package com.edutie.application.learning.learningexperience.implementation;

import com.edutie.application.learning.learningexperience.CreateLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.command.CreateLearningExperienceCommand;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateLearningExperienceCommandHandlerImplementationTest {
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private StudentPersistence studentPersistence;
    @Autowired
    private LearningExperiencePersistence learningExperiencePersistence;
    @Autowired
    private MockUser mockUser;

    private CreateLearningExperienceCommandHandler handler;
    private static LearningSubject learningSubject;

    @BeforeEach
    void setUpBeforeClass() {
        mockUser.saveToPersistence();

        learningSubject = LearningSubject.createBlank(mockUser.getEducatorProfile(), "Blue banana");
        learningSubject.appendRequirement("Req1", PromptFragment.empty());
        learningSubject.appendRequirement("Req2", PromptFragment.empty());
        learningSubject.appendRequirement("Req3", PromptFragment.empty());
        learningSubjectPersistence.save(learningSubject);
    }

    @Test
    public void handle() {
        handler = new CreateLearningExperienceCommandHandlerImplementation(
                learningExperiencePersistence,
                learningSubjectPersistence,
                studentPersistence,
                (o) -> WrapperResult.successWrapper(o.getRequirements().getFirst()),
                (s,k,req) -> WrapperResult.successWrapper(SimpleProblemActivityLearningExperience.create())
        );

        CreateLearningExperienceCommand command = new CreateLearningExperienceCommand()
                .studentUserId(mockUser.getUserId())
                .learningSubjectId(learningSubject.getId());

        WrapperResult<LearningExperience<?>> result = handler.handle(command);

        assertTrue(result.isSuccess());
        LearningExperience<?> learningExperience = result.getValue();
        assertTrue(learningExperiencePersistence.getById(learningExperience.getId()).isSuccess());
    }
}
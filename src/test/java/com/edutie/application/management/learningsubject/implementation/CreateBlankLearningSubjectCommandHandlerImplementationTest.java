package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.CreateBlankLearningSubjectCommandHandler;
import com.edutie.application.management.learningsubject.command.CreateBlankLearningSubjectCommand;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateBlankLearningSubjectCommandHandlerImplementationTest {
    @Autowired
    private EducatorPersistence educatorPersistence;
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;

    private CreateBlankLearningSubjectCommandHandler handler;

    @Autowired
    MockUser mockUser;

    @BeforeEach
    public void setUp() {
        mockUser.saveToPersistence();
    }

    @Test
    public void handle() {
        handler = new CreateBlankLearningSubjectCommandHandlerImplementation(educatorPersistence, learningSubjectPersistence);

        CreateBlankLearningSubjectCommand command = new CreateBlankLearningSubjectCommand()
                .learningSubjectName("Learning Subject")
                .educatorUserId(mockUser.getUserId());

        WrapperResult<LearningSubject> result = handler.handle(command);

        assertTrue(result.isSuccess());
        assertEquals("Learning Subject", result.getValue().getName());
    }
}
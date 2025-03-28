package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.learning.learningresult.CreateLearningResultCommandHandler;
import com.edutie.application.learning.learningresult.command.CreateLearningResultCommand;
import com.edutie.domain.core.learning.common.LearningObjectiveType;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.domain.core.learning.learningresult.entities.submission.SimpleProblemActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.service.LearningResultPersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.mocks.MockUser;
import com.edutie.mocks.persistence.learningexperience.CreateLearningResultLearningExperiencePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CreateLearningResultCommandHandlerImplementationTest {
    @Autowired
    private StudentPersistence studentPersistence;
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    private final LearningExperiencePersistence learningExperiencePersistence = new CreateLearningResultLearningExperiencePersistence(); //mock l.ex. persistence
    private final LearningResultPersonalizationService learningResultPersonalizationService = new LearningResultPersonalizationService() {
        @Override
        public <T extends SolutionSubmission> WrapperResult<LearningResult<?>> createPersonalized(Student student, LearningExperience<?> learningExperience, T solutionSubmission) {
            return WrapperResult.successWrapper(LearningResult.create(learningExperience, solutionSubmission, LearningEvaluation.create(Set.of())));
        }
    };

    @Autowired
    private MockUser mockUser;

    private CreateLearningResultCommandHandler createLearningResultCommandHandler;

    @BeforeEach
    void setUp() {
        mockUser.saveToPersistence();

        createLearningResultCommandHandler = new CreateLearningResultCommandHandlerImplementation(
                studentPersistence,
                learningExperiencePersistence,
                learningResultPersistence,
                learningResultPersonalizationService
        );
    }

    @Test
    void handle() {
        SolutionSubmission solutionSubmission = new SimpleProblemActivitySolutionSubmission();

        CreateLearningResultCommand<?> command = new CreateLearningResultCommand<>()
                .studentUserId(mockUser.getUserId())
                .learningExperienceId(new LearningExperienceId())
                .solutionSubmission(solutionSubmission);

        WrapperResult<LearningResult<?>> result = createLearningResultCommandHandler.handle(command);

        assertTrue(result.isSuccess());
        assertEquals(solutionSubmission, result.getValue().getSolutionSubmission());
    }
}
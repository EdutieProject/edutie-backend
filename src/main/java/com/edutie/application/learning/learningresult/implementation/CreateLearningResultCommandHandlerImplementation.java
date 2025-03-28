package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.learning.learningresult.CreateLearningResultCommandHandler;
import com.edutie.application.learning.learningresult.command.CreateLearningResultCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.service.LearningResultPersonalizationService;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateLearningResultCommandHandlerImplementation implements CreateLearningResultCommandHandler {
    private final StudentPersistence studentPersistence;
    private final LearningExperiencePersistence learningExperiencePersistence;
    private final LearningResultPersistence learningResultPersistence;
    private final LearningResultPersonalizationService learningResultPersonalizationService;

    @Override
    public WrapperResult<LearningResult<?>> handle(CreateLearningResultCommand<?> command) {
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningExperience<?> learningExperience = learningExperiencePersistence.getById(command.learningExperienceId()).getValue();
        SolutionSubmission solutionSubmission = command.solutionSubmission(); //todo!!!!
        LearningResult<?> learningResult = learningResultPersonalizationService.createPersonalized(student, learningExperience, solutionSubmission).getValue();
        learningResultPersistence.save(learningResult).throwIfFailure();
        return WrapperResult.successWrapper(learningResult);
    }
}

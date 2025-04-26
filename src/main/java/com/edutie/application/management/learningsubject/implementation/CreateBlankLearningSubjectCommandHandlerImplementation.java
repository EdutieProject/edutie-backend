package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.CreateBlankLearningSubjectCommandHandler;
import com.edutie.application.management.learningsubject.command.CreateBlankLearningSubjectCommand;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateBlankLearningSubjectCommandHandlerImplementation implements CreateBlankLearningSubjectCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningSubject> handle(CreateBlankLearningSubjectCommand command) {
        log.info("Creating blank learning subject for user id {}", command.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        LearningSubject learningSubject = LearningSubject.createBlank(educator, command.learningSubjectName());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        return WrapperResult.successWrapper(learningSubject);
    }
}

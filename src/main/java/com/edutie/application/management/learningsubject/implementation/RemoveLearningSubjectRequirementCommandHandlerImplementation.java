package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.RemoveLearningSubjectRequirementCommandHandler;
import com.edutie.application.management.learningsubject.command.RemoveLearningSubjectRequirementCommand;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class RemoveLearningSubjectRequirementCommandHandlerImplementation implements RemoveLearningSubjectRequirementCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningSubject> handle(RemoveLearningSubjectRequirementCommand command) {
        log.info("Removing the elemental requirement of id {} from learning subject of id {} by user of id {}", command.learningSubjectRequirementId(), command.learningSubjectId(), command.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(command.learningSubjectId()).getValue();
        educator.isAuthorOf(learningSubject).throwIfFailure();
        learningSubject.removeRequirement(command.learningSubjectRequirementId());
        learningSubjectPersistence.save(learningSubject);
        return WrapperResult.successWrapper(learningSubject);
    }
}

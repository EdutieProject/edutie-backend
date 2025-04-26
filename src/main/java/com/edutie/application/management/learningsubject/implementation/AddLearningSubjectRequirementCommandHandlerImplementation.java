package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.AddLearningSubjectRequirementCommandHandler;
import com.edutie.application.management.learningsubject.command.AddLearningSubjectRequirementCommand;
import com.edutie.domain.core.education.EducationError;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.education.learningsubject.service.StudentObjectiveInferringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddLearningSubjectRequirementCommandHandlerImplementation implements AddLearningSubjectRequirementCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final StudentObjectiveInferringService studentObjectiveInferringService;

    @Override
    public WrapperResult<LearningSubject> handle(AddLearningSubjectRequirementCommand command) {
        log.info("Adding a learning subject requirement for learning subject of id {} by user of id {}", command.learningSubjectId(), command.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(command.learningSubjectId()).getValue();
        educator.isAuthorOf(learningSubject).throwIfFailure();
        if (learningSubject.isKnowledgeOriginEmpty())
            return WrapperResult.failureWrapper(EducationError.learningSubjectEmptyKnowledgeOrigin());
        learningSubject.inferAndInsertRequirement(command.requirementTitle(), command.requirementOrdinal(), studentObjectiveInferringService).throwIfFailure();
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        return WrapperResult.successWrapper(learningSubject);
    }
}

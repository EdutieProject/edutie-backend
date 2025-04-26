package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.SetLearningSubjectKnowledgeSubjectCommandHandler;
import com.edutie.application.management.learningsubject.command.SetLearningSubjectKnowledgeSubjectCommand;
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
public class SetLearningSubjectKnowledgeSubjectCommandHandlerImplementation implements SetLearningSubjectKnowledgeSubjectCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningSubject> handle(SetLearningSubjectKnowledgeSubjectCommand command) {
        log.info("Adding knowledge subject id for learning subject {} knowledge origin's, for user id {}", command.learningSubjectId(), command.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(command.learningSubjectId()).getValue();
        educator.isAuthorOf(learningSubject).throwIfFailure();
        learningSubject.setRelatedKnowledgeSubjectId(command.knowledgeSubjectId());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();
        return WrapperResult.successWrapper(learningSubject);
    }
}

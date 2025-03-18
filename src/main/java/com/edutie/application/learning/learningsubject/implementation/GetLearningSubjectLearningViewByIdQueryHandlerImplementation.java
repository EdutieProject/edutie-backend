package com.edutie.application.learning.learningsubject.implementation;

import com.edutie.application.learning.learningsubject.GetLearningSubjectLearningViewByIdQueryHandler;
import com.edutie.application.learning.learningsubject.query.GetLearningSubjectStudentViewByIdQuery;
import com.edutie.application.learning.learningsubject.view.LearningSubjectLearningView;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetLearningSubjectLearningViewByIdQueryHandlerImplementation implements GetLearningSubjectLearningViewByIdQueryHandler {
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningSubjectLearningView> handle(GetLearningSubjectStudentViewByIdQuery query) {
        log.info("Retrieving learning subject's learning view for student user id {} using learning subject id {}", query.studentUserId(), query.learningSubjectId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(query.learningSubjectId()).getValue();
        return WrapperResult.successWrapper(new LearningSubjectLearningView(learningSubject, null, 0));
    }
}

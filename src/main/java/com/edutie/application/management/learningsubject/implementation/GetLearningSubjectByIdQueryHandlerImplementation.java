package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.GetLearningSubjectByIdQueryHandler;
import com.edutie.application.management.learningsubject.query.GetLearningSubjectByIdQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetLearningSubjectByIdQueryHandlerImplementation implements GetLearningSubjectByIdQueryHandler {
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningSubject> handle(GetLearningSubjectByIdQuery query) {
        log.info("Retrieving learning subject by its id {} for educator user of id {}", query.learningSubjectId(), query.educatorUserId());
        return learningSubjectPersistence.getById(query.learningSubjectId());
    }
}

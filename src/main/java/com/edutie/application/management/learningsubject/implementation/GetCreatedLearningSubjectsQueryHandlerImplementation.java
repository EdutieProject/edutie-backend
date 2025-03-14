package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.GetCreatedLearningSubjectsQueryHandler;
import com.edutie.application.management.learningsubject.query.GetCreatedLearningSubjectsQuery;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetCreatedLearningSubjectsQueryHandlerImplementation implements GetCreatedLearningSubjectsQueryHandler {
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<List<LearningSubject>> handle(GetCreatedLearningSubjectsQuery query) {
        log.info("Retrieving created learning subjects for user of id {}", query.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(query.educatorUserId());
        return learningSubjectPersistence.getCreatedLearningSubjects(educator);
    }
}

package com.edutie.application.learning.learningsubject.implementation;

import com.edutie.application.learning.learningsubject.GetCreatedEligibleLearningSubjectsQueryHandler;
import com.edutie.application.learning.learningsubject.query.GetCreatedEligibleLearningSubjectsQuery;
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
//todo: test & document this shit
public class GetCreatedEligibleLearningSubjectsQueryHandlerImplementation implements GetCreatedEligibleLearningSubjectsQueryHandler {
    private final EducatorPersistence educatorPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<List<LearningSubject>> handle(GetCreatedEligibleLearningSubjectsQuery query) {
        log.info("Retrieving eligible learning subjects for educator user id {}", query.studentUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(query.studentUserId());
        return learningSubjectPersistence.getCreatedLearningSubjects(educator)
                .map(o -> o.stream().filter(LearningSubject::isLearningEligible).toList());
    }
}

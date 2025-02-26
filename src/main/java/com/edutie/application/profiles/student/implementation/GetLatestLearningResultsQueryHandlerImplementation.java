package com.edutie.application.profiles.student.implementation;

import com.edutie.application.profiles.student.GetLatestLearningResultsQueryHandler;
import com.edutie.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetLatestLearningResultsQueryHandlerImplementation implements GetLatestLearningResultsQueryHandler {
    private final LearningResultPersistence learningResultPersistence;
    private final StudentPersistence studentPersistence;
    @Override
    public WrapperResult<List<LearningResult>> handle(GetLatestLearningResultsQuery query) {
        log.info("Retrieving latest learning results for student user of id {}, amount: {}, maxDate: {}",
                query.studentUserId(), query.amount(), query.maxDate());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        return learningResultPersistence.getLatestResultsOfStudent(student.getId(), query.amount(), query.maxDate());
    }
}

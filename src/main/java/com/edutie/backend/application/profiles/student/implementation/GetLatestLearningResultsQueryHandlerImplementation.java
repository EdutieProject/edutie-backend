package com.edutie.backend.application.profiles.student.implementation;

import com.edutie.backend.application.profiles.student.GetLatestLearningResultsQueryHandler;
import com.edutie.backend.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
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
        log.info("Retrieving latest learning results for student user of id {}", query.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        return learningResultPersistence.getLatestResultsOfStudent(student.getId(), query.amount());
    }
}

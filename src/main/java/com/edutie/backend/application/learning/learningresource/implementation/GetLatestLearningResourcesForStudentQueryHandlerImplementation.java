package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.GetLatestLearningResourcesForStudentQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLatestLearningResourcesForStudentQueryHandlerImplementation implements GetLatestLearningResourcesForStudentQueryHandler {
    private final LearningResourcePersistence learningResourcePersistence;
    private final StudentPersistence studentPersistence;

    @Override
    public WrapperResult<List<LearningResource>> handle(GetLearningResourcesByDefinitionIdQuery query) {
        log.info("Retrieving latest learning resources for student user of id {}", query.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        return learningResourcePersistence.getLatestLearningResourcesForStudent(student.getId());
    }
}

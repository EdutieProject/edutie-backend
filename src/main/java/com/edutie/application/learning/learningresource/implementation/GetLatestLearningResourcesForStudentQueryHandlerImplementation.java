package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.learning.learningresource.GetLatestLearningResourcesForStudentQueryHandler;
import com.edutie.application.learning.learningresource.queries.GetLatestLearningResourcesForStudentQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLatestLearningResourcesForStudentQueryHandlerImplementation implements GetLatestLearningResourcesForStudentQueryHandler {
    private final LearningExperiencePersistence learningExperiencePersistence;
    private final StudentPersistence studentPersistence;

    @Override
    public WrapperResult<List<LearningExperience>> handle(GetLatestLearningResourcesForStudentQuery query) {
        log.info("Retrieving latest learning resources for student user of id {}", query.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        return learningExperiencePersistence.getLatestForStudent(student.getId());
    }
}

package com.edutie.backend.application.learning.course.implementation;

import com.edutie.backend.application.learning.course.CoursesByStudentProgressQueryHandler;
import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CoursesByStudentProgressQueryHandlerImplementation extends HandlerBase implements CoursesByStudentProgressQueryHandler {
    private final StudentPersistence studentPersistence;
    private final CoursePersistence coursePersistence;
    private final SciencePersistence sciencePersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesByStudentProgressQuery query) {
        LOGGER.info("Retrieving all courses in progress for user of id {}", query.studentUserId().identifierValue());
//        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
//        List<Course> coursesInProgress = student
//                .getLearningHistory().stream()
//                .map(o -> o.getSegment().getLesson().getCourse())
//                .collect(Collectors.toSet())
//                .stream().toList();
        List<Course> coursesInProgress = sciencePersistence.getAll().getValue()
                .stream().flatMap(o -> coursePersistence.getAllOfScienceId(o.getId()).getValue().stream())
                .collect(Collectors.toList());
        LOGGER.info("Courses retrieved successfully.");
        return WrapperResult.successWrapper(coursesInProgress);
    }
}

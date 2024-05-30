package com.edutie.backend.application.learning.course.implementation;

import com.edutie.backend.application.learning.course.CoursesByStudentProgressQueryHandler;
import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CoursesByStudentProgressQueryHandlerImplementation extends HandlerBase implements CoursesByStudentProgressQueryHandler {
    private final StudentPersistence studentPersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesByStudentProgressQuery query) {
        LOGGER.info("Retrieving all courses in progress for user of id {}", query.studentUserId().identifierValue());
        Student student = studentPersistence.getByUserId(query.studentUserId());
        List<Course> coursesInProgress = student
                .getLearningHistory().stream()
                .map(o -> o.getSegment().getLesson().getCourse())
                .collect(Collectors.toSet())
                .stream().toList();
        LOGGER.info("Courses retrieved successfully.");
        return WrapperResult.successWrapper(coursesInProgress);
    }
}

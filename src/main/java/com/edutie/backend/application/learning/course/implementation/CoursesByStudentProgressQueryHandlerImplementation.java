package com.edutie.backend.application.learning.course.implementation;

import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.application.shared.UseCaseHandlerBase;
import com.edutie.backend.application.shared.UseCaseHandler;
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
public class CoursesByStudentProgressQueryHandlerImplementation extends UseCaseHandlerBase implements UseCaseHandler<WrapperResult<List<Course>>, CoursesByStudentProgressQuery> {
    private final StudentPersistence studentPersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesByStudentProgressQuery query) {
        LOGGER.info("Retrieving all courses in progress for student of id {}", query.studentId().identifierValue());
        Student student = studentPersistence.getById(query.studentId()).getValue();
        List<Course> coursesInProgress = student
                .getLearningHistory().stream()
                .map(o -> o.getLessonSegment().getLesson().getCourse())
                .collect(Collectors.toSet())
                .stream().toList();
        return WrapperResult.successWrapper(coursesInProgress);
    }
}

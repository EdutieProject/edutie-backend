package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.learning.studyprogram.CourseByIdQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.CourseByIdQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseByIdQueryHandlerImplementation implements CourseByIdQueryHandler {
    private final CoursePersistence coursePersistence;
    @Override
    public WrapperResult<Course> handle(CourseByIdQuery query) {
        log.info("Retrieving course by Id - courseId: {}, by student user of id: {}", query.courseId(), query.studentUserId());
        return coursePersistence.getById(query.courseId());
    }
}

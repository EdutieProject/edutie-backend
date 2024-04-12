package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.UseCaseHandlerBase;
import com.edutie.backend.application.management.course.CoursesFromEducatorQueryHandler;
import com.edutie.backend.application.management.course.queries.CoursesFromEducatorQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
@Component
@RequiredArgsConstructor
public class CoursesFromEducatorQueryHandlerImplementation extends UseCaseHandlerBase implements CoursesFromEducatorQueryHandler {
    private final CoursePersistence coursePersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesFromEducatorQuery coursesFromEducatorQuery) {
        return WrapperResult.successWrapper(coursePersistence.getAllOfEducatorId(coursesFromEducatorQuery.educatorId()));
    }
}

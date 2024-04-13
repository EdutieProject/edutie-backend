package com.edutie.backend.application.management.course;

import com.edutie.backend.application.management.course.queries.CoursesFromEducatorQuery;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

import java.util.List;

public interface CoursesFromEducatorQueryHandler
        extends UseCaseHandler<WrapperResult<List<Course>>, CoursesFromEducatorQuery> {
}

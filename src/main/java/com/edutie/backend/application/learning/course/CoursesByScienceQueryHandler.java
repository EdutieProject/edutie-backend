package com.edutie.backend.application.learning.course;

import com.edutie.backend.application.learning.course.queries.CoursesByScienceQuery;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

import java.util.List;

public interface CoursesByScienceQueryHandler extends UseCaseHandler<WrapperResult<List<Course>>, CoursesByScienceQuery> {
}

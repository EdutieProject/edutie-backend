package com.edutie.backend.application.learning.course;

import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.application.shared.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

import java.util.List;

public interface CoursesQueryByStudentProgressHandler extends UseCaseHandler<WrapperResult<List<Course>>, CoursesByStudentProgressQuery> {
}

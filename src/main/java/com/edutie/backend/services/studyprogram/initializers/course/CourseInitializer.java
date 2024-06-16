package com.edutie.backend.services.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import validation.Result;
import validation.WrapperResult;

@FunctionalInterface
public interface CourseInitializer {
    Result initializeCourse(Course course);
}

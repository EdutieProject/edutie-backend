package com.edutie.backend.services.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

@FunctionalInterface
public interface CourseInitializer {
    WrapperResult<Course> initializeCourse(CourseInitializationDetails initializationDetails);
}

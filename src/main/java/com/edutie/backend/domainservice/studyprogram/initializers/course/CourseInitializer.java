package com.edutie.backend.domainservice.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import validation.Result;

@FunctionalInterface
public interface CourseInitializer {
    Result initializeCourse(Course course);
}

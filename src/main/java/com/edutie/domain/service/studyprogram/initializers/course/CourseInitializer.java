package com.edutie.domain.service.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import validation.Result;

@FunctionalInterface
public interface CourseInitializer {
	Result initializeCourse(Course course);
}

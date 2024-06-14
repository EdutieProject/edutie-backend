package com.edutie.backend.services.studyprogram.creators.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

@FunctionalInterface
public interface CourseCreator {
    WrapperResult<Course> initializeCourse(CourseCreationDetails initializationDetails);
}

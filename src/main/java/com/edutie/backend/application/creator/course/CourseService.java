package com.edutie.backend.application.creator.course;

import com.edutie.backend.application.creator.course.commands.ChangeCourseAccessibilityCommand;
import com.edutie.backend.application.creator.course.commands.ChangeCoursePropertiesCommand;
import com.edutie.backend.application.creator.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface CourseService {
    List<Course> getAllCoursesFromCreator(CreatorId creatorId);
    WrapperResult<Course> createCourse(CreateCourseCommand command);
    Result changeCourseProperties(ChangeCoursePropertiesCommand command);
    Result changeCourseAccessibility(ChangeCourseAccessibilityCommand command);
}

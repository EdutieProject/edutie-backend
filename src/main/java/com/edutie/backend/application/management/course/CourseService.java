package com.edutie.backend.application.management.course;

import com.edutie.backend.application.management.course.commands.ChangeCourseAccessibilityCommand;
import com.edutie.backend.application.management.course.commands.ChangeCoursePropertiesCommand;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

/**
 * Service interface for managing courses in the educational system.
 */
public interface CourseService {

    /**
     * Retrieves a list of all courses created by the specified educator.
     *
     * @param educatorId The identifier of the educator.
     * @return A list of courses created by the educator.
     */
    List<Course> getAllCoursesFromEducator(EducatorId educatorId);

    /**
     * Creates a new course based on the provided command.
     *
     * @param command The command containing information for creating the course.
     * @return A {@code WrapperResult} containing the created course or any associated errors.
     */
    WrapperResult<Course> createCourse(CreateCourseCommand command);

    /**
     * Changes the properties of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course properties.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result changeCourseProperties(ChangeCoursePropertiesCommand command);

    /**
     * Changes the accessibility of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course accessibility.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    Result changeCourseAccessibility(ChangeCourseAccessibilityCommand command);
}

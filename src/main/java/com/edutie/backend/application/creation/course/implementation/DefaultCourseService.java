package com.edutie.backend.application.creation.course.implementation;

import com.edutie.backend.application.creation.course.CourseService;
import com.edutie.backend.application.creation.course.commands.ChangeCourseAccessibilityCommand;
import com.edutie.backend.application.creation.course.commands.ChangeCoursePropertiesCommand;
import com.edutie.backend.application.creation.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.contexts.studyprogram.CoursePersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@Component
public class DefaultCourseService implements CourseService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CoursePersistenceContext coursePersistenceContext;

    public DefaultCourseService(CoursePersistenceContext coursePersistenceContext){
        this.coursePersistenceContext = coursePersistenceContext;
    }


    /**
     * Retrieves a list of all courses created by the specified educator.
     *
     * @param educatorId The identifier of the educator.
     * @return A list of courses created by the educator.
     */
    @Override
    public List<Course> getAllCoursesFromEducator(EducatorId educatorId) {
        return null;
    }

    /**
     * Creates a new course based on the provided command.
     *
     * @param command The command containing information for creating the course.
     * @return A {@code WrapperResult} containing the created course or any associated errors.
     */
    @Override
    public WrapperResult<Course> createCourse(CreateCourseCommand command) {
        LOGGER.info("Creating course by educator of id {}", command.educatorId().identifierValue());
        return null;
    }

    /**
     * Changes the properties of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course properties.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    @Override
    public Result changeCourseProperties(ChangeCoursePropertiesCommand command) {
        return null;
    }

    /**
     * Changes the accessibility of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course accessibility.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    @Override
    public Result changeCourseAccessibility(ChangeCourseAccessibilityCommand command) {
        return null;
    }
}

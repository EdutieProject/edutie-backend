package com.edutie.backend.application.creation.course.implementation;

import com.edutie.backend.application.creation.course.CourseService;
import com.edutie.backend.application.creation.course.commands.ChangeCourseAccessibilityCommand;
import com.edutie.backend.application.creation.course.commands.ChangeCoursePropertiesCommand;
import com.edutie.backend.application.creation.course.commands.CreateCourseCommand;
import com.edutie.backend.application.shared.ApplicationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultCourseService implements CourseService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    private final SciencePersistence sciencePersistence;


    /**
     * Retrieves a list of all courses created by the specified educator.
     *
     * @param educatorId The identifier of the educator.
     * @return A list of courses created by the educator.
     */
    @Override
    public List<Course> getAllCoursesFromEducator(EducatorId educatorId) {
        LOGGER.info("Retrieving all courses made by educator of id {}", educatorId.identifierValue());
        Educator educator = educatorPersistence.getById(educatorId).getValue();
        return coursePersistence.getAllOfEducatorId(educatorId);
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
        Educator educator = educatorPersistence.getById(command.educatorId()).getValue();
        WrapperResult<Science> scienceResult = sciencePersistence.getById(command.scienceId());
        if (scienceResult.isFailure()) {
            LOGGER.info("Science of id {} was not found", command.scienceId().identifierValue());
        }
        Course course = Course.create(educator, scienceResult.getValue());
        course.setName(command.courseName());
        course.setDescription(command.courseDescription());
        coursePersistence.save(course);
        LOGGER.info("Course creation success. Course id: {}", course.getId().identifierValue());
        return WrapperResult.successWrapper(course);
    }

    /**
     * Changes the properties of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course properties.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    @Override
    public Result changeCourseProperties(ChangeCoursePropertiesCommand command) {
        LOGGER.info("Modifying course of id {} by educator of id {}", command.courseId().identifierValue(), command.educatorId().identifierValue());
        WrapperResult<Course> courseSearchResult = coursePersistence.getById(command.courseId());
        if (courseSearchResult.isFailure()) {
            LOGGER.info("Course of id {} has not been found in persistence", command.courseId().identifierValue());
            return Result.failure(ApplicationError.persistenceOperationError());
        }
        Course course = courseSearchResult.getValue();
        Educator educator = educatorPersistence.getById(command.educatorId()).getValue();
        if (!course.getEducator().equals(educator)) {
            LOGGER.info("Educator of id {} did not create the course of provided id. Therefore, they have no privilege " +
                    "of modifying this course properties.", command.educatorId().identifierValue());
            return Result.failure(ApplicationError.authorizationError());
        }
        if (command.courseName() != null) course.setName(command.courseName());
        if (command.courseDescription() != null) course.setDescription(command.courseDescription());
        course.setUpdatedBy(educator.getCreatedBy());
        coursePersistence.save(course);
        return Result.success();
    }

    /**
     * Changes the accessibility of an existing course based on the provided command.
     *
     * @param command The command containing information for changing course accessibility.
     * @return A {@code Result} indicating the success or failure of the operation.
     */
    @Override
    public Result changeCourseAccessibility(ChangeCourseAccessibilityCommand command) {
        LOGGER.info("Modifying course accessibility of id {} by educator of id {}", command.courseId().identifierValue(), command.educatorId().identifierValue());
        WrapperResult<Course> courseSearchResult = coursePersistence.getById(command.courseId());
        if (courseSearchResult.isFailure()) {
            LOGGER.info("Course of id {} has not been found in persistence", command.courseId().identifierValue());
            return Result.failure(ApplicationError.persistenceOperationError());
        }
        Course course = courseSearchResult.getValue();
        Educator educator = educatorPersistence.getById(command.educatorId()).getValue();
        if (!course.getEducator().equals(educator)) {
            LOGGER.info("Educator of id {} did not create the course of provided id. Therefore, they have no privilege " +
                    "of modifying this course properties.", command.educatorId().identifierValue());
            return Result.failure(ApplicationError.authorizationError());
        }
        course.setAccessible(command.accessibility());
        course.setUpdatedBy(educator.getCreatedBy());
        return Result.success();
    }
}

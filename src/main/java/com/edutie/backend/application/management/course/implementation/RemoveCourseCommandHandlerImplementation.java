package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.errors.EducatorError;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class RemoveCourseCommandHandlerImplementation extends HandlerBase implements RemoveCourseCommandHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(RemoveCourseCommand command) {
        LOGGER.info("Removing course of id {}", command.courseId().identifierValue());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        WrapperResult<Course> courseWrapperResult = coursePersistence.getById(command.courseId());
        if (courseWrapperResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + courseWrapperResult.getError().toString());
            return courseWrapperResult;
        }
        Course course = courseWrapperResult.getValue();
        if (!course.getEducator().equals(educator)) {
            LOGGER.info("Educator does not have sufficient permissions to modify this course");
            return Result.failure(EducatorError.mustBeOwnerError(Course.class));
        }
        coursePersistence.remove(course);
        LOGGER.info("Course removed successfully");
        return coursePersistence.remove(course);
    }
}

package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifyCourseCommandHandlerImplementation extends HandlerBase implements ModifyCourseCommandHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result handle(ModifyCourseCommand command) {
        LOGGER.info("Modifying course of id {} by educator of id {}",
                command.courseId().identifierValue(),
                command.educatorUserId().identifierValue());
        WrapperResult<Course> courseWrapperResult = coursePersistence.getById(command.courseId());
        if (courseWrapperResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + courseWrapperResult.getError().toString());
            return courseWrapperResult;
        }
        Course course = courseWrapperResult.getValue();
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        if (!educator.isAuthorOf(course)) {
            LOGGER.info("Educator does not have sufficient permissions to modify this course");
            return Result.failure(EducationError.educatorMustBeAuthorError(Course.class));
        }
        if (command.courseName() != null) course.setName(command.courseName());
        if (command.courseDescription() != null) course.setDescription(command.courseDescription());
        if (command.accessibility() != null) course.setAccessible(command.accessibility());
        course.update(command.educatorUserId());
        Result saveResult = coursePersistence.save(course);
        if (saveResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + saveResult.getError().toString());
            return saveResult;
        }
        LOGGER.info("Course modification successful");
        return Result.success();
    }
}

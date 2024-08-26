package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domainservice.studyprogram.initializers.course.CourseInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateCourseCommandHandlerImplementation extends HandlerBase implements CreateCourseCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;

    private final CourseInitializer courseInitializer;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Course> handle(CreateCourseCommand command) {
        LOGGER.info("Creating course by user of id {} ", command.educatorUserId().identifierValue());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        Science science = sciencePersistence.getById(command.scienceId()).getValue();
        Course course = Course.create(educator, science);
        course.setName(command.courseName());
        course.setDescription(command.courseDescription() != null ? command.courseDescription() : "");
        coursePersistence.save(course).throwIfFailure();
        courseInitializer.initializeCourse(course).throwIfFailure();
        LOGGER.info("Course creation & initialization success.");
        return WrapperResult.successWrapper(course);
    }
}

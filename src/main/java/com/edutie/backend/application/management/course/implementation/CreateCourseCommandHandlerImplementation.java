package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.services.studyprogram.creators.course.CourseCreationDetails;
import com.edutie.backend.services.studyprogram.creators.course.CourseCreator;
import com.edutie.backend.services.studyprogram.initializers.lesson.RootLessonInitializer;
import com.edutie.backend.services.studyprogram.initializers.segment.RootSegmentInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateCourseCommandHandlerImplementation extends HandlerBase implements CreateCourseCommandHandler {
    private final EducatorPersistence educatorPersistence;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;

    private final CourseCreator courseCreator;
    private final RootLessonInitializer rootLessonInitializer;
    private final RootSegmentInitializer rootSegmentInitializer;

    @Override
    public WrapperResult<Course> handle(CreateCourseCommand command) {
        LOGGER.info("Creating course by user of id {} ", command.educatorUserId().identifierValue());
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        Science science = sciencePersistence.getById(command.scienceId()).getValue();
        WrapperResult<Course> courseInitializationResult = courseCreator.initializeCourse(
                new CourseCreationDetails().educator(educator).science(science)
                        .name(command.courseName()).description(command.courseDescription())
        );
        if (courseInitializationResult.isFailure())
            return courseInitializationResult;
        LOGGER.info("Course creation & initialization success.");
        return WrapperResult.successWrapper(courseInitializationResult.getValue());
    }
}

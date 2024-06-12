package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.services.studyprogram.initializers.course.CourseInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.course.CourseInitializer;
import com.edutie.backend.services.studyprogram.initializers.lesson.LessonInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.lesson.LessonInitializer;
import com.edutie.backend.services.studyprogram.initializers.segment.SegmentInitializationDetails;
import com.edutie.backend.services.studyprogram.initializers.segment.SegmentInitializer;
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

    private final CourseInitializer courseInitializer;
    private final LessonInitializer lessonInitializer;
    private final SegmentInitializer segmentInitializer;

    @Override
    public WrapperResult<Course> handle(CreateCourseCommand command) {
        LOGGER.info("Creating course by user of id {} ", command.educatorUserId().identifierValue());
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        Science science = sciencePersistence.getById(command.scienceId()).getValue();
        WrapperResult<Course> initializedCourse = courseInitializer.initializeCourse(
                new CourseInitializationDetails().educator(educator).science(science)
                        .name(command.courseName()).description(command.courseDescription())
        );
        if (initializedCourse.isFailure())
            return initializedCourse;
        WrapperResult<Lesson> initializedFirstLesson = lessonInitializer.initializeLesson(
                new LessonInitializationDetails().course(initializedCourse.getValue()).educator(educator)
                        .name("The first lesson")
                        .description("Sample description. Modify it as you like!")
        );
        if (initializedFirstLesson.isFailure())
            return initializedFirstLesson.map(o -> null);
        segmentInitializer.initializeSegment(
                new SegmentInitializationDetails().educator(educator)
                        .lesson(initializedFirstLesson.getValue()).name("The first segment")
        );
        LOGGER.info("Course creation & initialization success.");
        return WrapperResult.successWrapper(initializedCourse.getValue());
    }
}

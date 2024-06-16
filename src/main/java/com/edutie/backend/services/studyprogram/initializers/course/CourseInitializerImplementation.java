package com.edutie.backend.services.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.services.common.ServiceBase;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.services.studyprogram.initializers.lesson.LessonInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
public class CourseInitializerImplementation extends ServiceBase implements CourseInitializer {
    private final LessonPersistence lessonPersistence;
    private final LessonInitializer lessonInitializer;

    @Override
    public Result initializeCourse(Course course) {
        LOGGER.info("Initializing course with an initial root lesson.");
        Lesson initalLesson = Lesson.create(course.getEducator(), course);
        initalLesson.setName("Initial lesson");
        initalLesson.setDescription("Modify this lesson as you like.");
        Result lessonSaveResult = lessonPersistence.save(initalLesson);
        if (lessonSaveResult.isFailure())
            return ExternalFailureLog.persistenceFailure(lessonSaveResult, LOGGER);
        Result lessonInitializationResult = lessonInitializer.initializeLesson(initalLesson);
        if (lessonInitializationResult.isFailure()) {
            LOGGER.warn("Lesson initialization failed!");
            return lessonInitializationResult;
        }
        LOGGER.info("Course initialization success.");
        return Result.success();
    }
}

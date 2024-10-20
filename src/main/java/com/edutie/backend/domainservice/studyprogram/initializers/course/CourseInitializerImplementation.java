package com.edutie.backend.domainservice.studyprogram.initializers.course;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domainservice.studyprogram.initializers.lesson.LessonInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseInitializerImplementation implements CourseInitializer {
    private final LessonPersistence lessonPersistence;
    private final LessonInitializer lessonInitializer;

    @Override
    public Result initializeCourse(Course course) {
        log.info("Initializing course with an initial root lesson.");
        Lesson initalLesson = Lesson.create(course.getAuthorEducator(), course);
        initalLesson.setName("Initial lesson");
        initalLesson.setDescription("Modify this lesson as you like.");
        lessonPersistence.save(initalLesson).throwIfFailure();
        lessonInitializer.initializeLesson(initalLesson).throwIfFailure();
        log.info("Course initialization success.");
        return Result.success();
    }
}

package com.edutie.backend.services.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

@FunctionalInterface
public interface LessonInitializer {
    WrapperResult<Lesson> initializeLesson(LessonInitializationDetails lessonInitializationDetails);
}

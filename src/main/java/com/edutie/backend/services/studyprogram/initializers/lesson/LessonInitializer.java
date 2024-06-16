package com.edutie.backend.services.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.Result;
import validation.WrapperResult;

@FunctionalInterface
public interface LessonInitializer {
    Result initializeLesson(Lesson lesson);
}

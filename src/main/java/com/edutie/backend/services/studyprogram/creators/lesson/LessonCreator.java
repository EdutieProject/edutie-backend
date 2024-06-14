package com.edutie.backend.services.studyprogram.creators.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

@FunctionalInterface
public interface LessonCreator {
    WrapperResult<Lesson> createLesson(LessonCreationDetails lessonCreationDetails);
}

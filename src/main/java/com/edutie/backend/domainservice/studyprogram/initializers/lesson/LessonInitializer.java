package com.edutie.backend.domainservice.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.Result;

@FunctionalInterface
public interface LessonInitializer {
	Result initializeLesson(Lesson lesson);
}

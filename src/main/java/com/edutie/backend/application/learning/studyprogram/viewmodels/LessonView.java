package com.edutie.backend.application.learning.studyprogram.viewmodels;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domainservice.studyprogram.progressindication.lesson.LessonProgressState;

public record LessonView(
		Lesson lesson,
		LessonProgressState progressState) {
}

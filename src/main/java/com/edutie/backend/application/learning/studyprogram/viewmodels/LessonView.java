package com.edutie.backend.application.learning.studyprogram.viewmodels;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;

public record LessonView(
        Lesson lesson,
        boolean done
) {
}

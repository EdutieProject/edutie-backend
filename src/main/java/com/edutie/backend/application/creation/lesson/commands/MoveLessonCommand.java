package com.edutie.backend.application.creation.lesson.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record MoveLessonCommand(
        EducatorId educatorId,
        LessonId lessonId,
        LessonId previousLessonId,
        LessonId nextLessonId
) {
}

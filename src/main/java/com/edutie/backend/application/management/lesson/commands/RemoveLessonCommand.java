package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record RemoveLessonCommand(
        EducatorId educatorId,
        LessonId lessonId
) {
}

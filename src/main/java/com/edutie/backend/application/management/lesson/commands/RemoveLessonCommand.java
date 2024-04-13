package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record RemoveLessonCommand(
        UserId educatorUserId,
        LessonId lessonId
) {
}

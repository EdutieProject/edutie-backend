package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record ModifyLessonCommand(
        UserId educatorUserId,
        LessonId lessonId,
        String lessonName,
        String lessonDescription,
        LessonId previousLessonId,
        LessonId nextLessonId
) {
}

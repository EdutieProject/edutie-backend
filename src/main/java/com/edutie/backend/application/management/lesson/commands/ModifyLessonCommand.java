package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.NonNull;

public record ModifyLessonCommand(
        @NonNull UserId educatorUserId,
        @NonNull LessonId lessonId,
        String lessonName,
        String lessonDescription,
        LessonId previousLessonId,
        LessonId nextLessonId
) {
}

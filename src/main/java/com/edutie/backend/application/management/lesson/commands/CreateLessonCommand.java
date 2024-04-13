package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.NonNull;

public record CreateLessonCommand(
        @NonNull UserId educatorUserId,
        @NonNull String lessonName,
        String lessonDescription,
        @NonNull LessonId previousLessonId,
        LessonId nextLessonId
) {
}
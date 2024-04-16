package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.NonNull;

public record RemoveLessonCommand(
        @NonNull UserId educatorUserId,
        @NonNull LessonId lessonId
) {
}

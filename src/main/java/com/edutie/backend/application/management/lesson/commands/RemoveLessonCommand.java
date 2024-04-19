package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public final class RemoveLessonCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull LessonId lessonId;
}

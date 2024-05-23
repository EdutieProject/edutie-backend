package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
public final class CreateLessonCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull String lessonName;
    private String lessonDescription;
    private @NonNull LessonId previousLessonId;
    private LessonId nextLessonId;
}
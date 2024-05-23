package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(fluent = true)
public final class ModifyLessonCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull LessonId lessonId;
    private String lessonName;
    private String lessonDescription;
    private LessonId previousLessonId;
    private List<LessonId> nextLessonIds = new ArrayList<>();
}

package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateLessonCommand {
    @JsonIgnore
    private @NonNull UserId educatorUserId;
    private @NonNull String lessonName;
    private String lessonDescription;
    private @NonNull LessonId previousLessonId;
    private LessonId nextLessonId;
}
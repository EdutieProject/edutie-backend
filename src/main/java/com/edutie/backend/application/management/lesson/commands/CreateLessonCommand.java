package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateLessonCommand extends EducatorAction<CreateLessonCommand> {
    private @NonNull String lessonName;
    private String lessonDescription;
    private @NonNull LessonId previousLessonId;
    private LessonId nextLessonId;

    @Override
    protected CreateLessonCommand getThis() {
        return this;
    }
}
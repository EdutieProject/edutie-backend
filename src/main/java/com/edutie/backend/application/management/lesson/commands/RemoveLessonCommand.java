package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
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
public final class RemoveLessonCommand extends EducatorAction<RemoveLessonCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LessonId lessonId;

    @Override
    protected RemoveLessonCommand getThis() {
        return this;
    }
}

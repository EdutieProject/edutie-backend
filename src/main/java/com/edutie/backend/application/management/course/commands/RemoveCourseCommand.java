package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
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
public final class RemoveCourseCommand extends EducatorAction<RemoveCourseCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull CourseId courseId;
    @Schema(example = "false")
    private boolean removeLessons = false;

    @Override
    protected RemoveCourseCommand getThis() {
        return this;
    }
}

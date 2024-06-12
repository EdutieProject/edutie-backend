package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
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
public final class CreateCourseCommand extends EducatorAction<CreateCourseCommand> {
    private @NonNull String courseName;
    private String courseDescription;
    private @NonNull ScienceId scienceId;

    @Override
    protected CreateCourseCommand getThis() {
        return this;
    }
}

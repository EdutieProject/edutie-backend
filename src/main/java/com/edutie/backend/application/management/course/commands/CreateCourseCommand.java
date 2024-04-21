package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public final class CreateCourseCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull String courseName;
    private String courseDescription;
    private @NonNull ScienceId scienceId;
}

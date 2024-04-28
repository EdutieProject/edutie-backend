package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public final class CreateCourseCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull String courseName;
    private String courseDescription;
    private @NonNull ScienceId scienceId;
}

package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import lombok.NonNull;

public record CreateCourseCommand(
        @NonNull UserId educatorUserId,
        @NonNull String courseName,
        String courseDescription,
        @NonNull ScienceId scienceId
) {
}

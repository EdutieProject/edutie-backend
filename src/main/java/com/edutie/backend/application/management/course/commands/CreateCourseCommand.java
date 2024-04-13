package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

public record CreateCourseCommand(
        UserId educatorUserId,
        String courseName,
        String courseDescription,
        ScienceId scienceId
) {
}

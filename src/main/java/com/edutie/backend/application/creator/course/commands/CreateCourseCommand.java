package com.edutie.backend.application.creator.course.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

public record CreateCourseCommand(
        CreatorId creatorId,
        String courseName,
        String courseDescription,
        ScienceId scienceId
) {
}

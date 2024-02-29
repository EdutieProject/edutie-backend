package com.edutie.backend.application.creator.course.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;

public record ChangeCourseAccessibilityCommand(
        CreatorId creatorId,
        boolean accessibility
) {
}

package com.edutie.backend.application.creator.course.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;

public record ChangeCourseAccessibilityCommand(
        EducatorId educatorId,
        boolean accessibility
) {
}

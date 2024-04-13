package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

public record ModifyCourseCommand(
        UserId educatorUserId,
        CourseId courseId,
        String courseName,
        String courseDescription,
        Boolean accessibility
) {
}

package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.NonNull;

public record ModifyCourseCommand(
        @NonNull UserId educatorUserId,
        @NonNull CourseId courseId,
        String courseName,
        String courseDescription,
        Boolean accessibility
) {
}

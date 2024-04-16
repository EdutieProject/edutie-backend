package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.NonNull;

public record RemoveCourseCommand(
        @NonNull UserId educatorUserId,
        @NonNull CourseId courseId
) {
}

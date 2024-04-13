package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

public record RemoveCourseCommand(
        UserId educatorUserId,
        CourseId courseId
) {
}

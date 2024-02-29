package com.edutie.backend.application.creator.course.commands;

import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;

public record ChangeCoursePropertiesCommand(
        CreatorId creatorId,
        CourseId courseId,
        String courseName,
        String courseDescription
) {
}

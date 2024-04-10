package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;

public record ModifyCourseCommand(
        EducatorId educatorId,
        CourseId courseId,
        String courseName,
        String courseDescription,
        Boolean accessibility
) {
}

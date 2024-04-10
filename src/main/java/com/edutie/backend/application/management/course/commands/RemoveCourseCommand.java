package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

public record RemoveCourseCommand(
        EducatorId educatorId,
        CourseId courseId
) {
}

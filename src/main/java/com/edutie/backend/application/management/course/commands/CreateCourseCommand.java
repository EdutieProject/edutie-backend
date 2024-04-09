package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

public record CreateCourseCommand(
        EducatorId educatorId,
        String courseName,
        String courseDescription,
        ScienceId scienceId
) {
}

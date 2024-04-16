package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import lombok.NonNull;

public record CoursesByScienceQuery(
        @NonNull ScienceId scienceId
) {
}

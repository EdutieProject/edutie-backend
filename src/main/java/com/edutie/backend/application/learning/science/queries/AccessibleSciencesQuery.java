package com.edutie.backend.application.learning.science.queries;

import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import lombok.NonNull;

public record AccessibleSciencesQuery(
        @NonNull ScienceId scienceId
) {
}
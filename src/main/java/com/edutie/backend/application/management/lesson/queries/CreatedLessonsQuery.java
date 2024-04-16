package com.edutie.backend.application.management.lesson.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record CreatedLessonsQuery(
        @NonNull UserId educatorUserId
) {
}

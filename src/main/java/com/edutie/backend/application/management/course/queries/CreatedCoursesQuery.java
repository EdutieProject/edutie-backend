package com.edutie.backend.application.management.course.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record CreatedCoursesQuery(
        @NonNull UserId educatorUserId
) {
}

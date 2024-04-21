package com.edutie.backend.application.management.course.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;

@Data
public final class CreatedCoursesQuery {
    private @NonNull UserId educatorUserId;
}

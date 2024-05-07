package com.edutie.backend.application.management.course.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public final class CreatedCoursesQuery {
    private @NonNull UserId educatorUserId;
}

package com.edutie.backend.application.management.lesson.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
public class CreatedLessonsQuery {
    private @NonNull UserId educatorUserId;
}

package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@NoArgsConstructor
@Accessors(fluent = true)
public final class RemoveCourseCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull CourseId courseId;
}

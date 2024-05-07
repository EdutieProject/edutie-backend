package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public final class ModifyCourseCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull CourseId courseId;
    private String courseName;
    private String courseDescription;
    private Boolean accessibility;
}

package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ModifyCourseCommand {
    @JsonIgnore
    private @NonNull UserId educatorUserId;
    private @NonNull CourseId courseId;
    private String courseName;
    private String courseDescription;
    private Boolean accessibility;
}

package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CoursesByScienceQuery {
    private @NonNull ScienceId scienceId;
}

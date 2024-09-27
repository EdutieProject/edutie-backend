package com.edutie.backend.application.learning.studyprogram.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CourseByIdQuery extends StudentAction<CourseByIdQuery> {
    private @NonNull CourseId courseId;

    @Override
    protected CourseByIdQuery getThis() {
        return this;
    }
}

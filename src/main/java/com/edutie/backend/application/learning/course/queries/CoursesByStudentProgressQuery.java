package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CoursesByStudentProgressQuery extends StudentAction<CoursesByStudentProgressQuery> {
    @Override
    protected CoursesByStudentProgressQuery getThis() {
        return this;
    }
}

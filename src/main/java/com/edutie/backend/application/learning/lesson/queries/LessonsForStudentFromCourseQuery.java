package com.edutie.backend.application.learning.lesson.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public final class LessonsForStudentFromCourseQuery extends StudentAction<LessonsForStudentFromCourseQuery> {
    private @NonNull CourseId courseId;

    @Override
    protected LessonsForStudentFromCourseQuery getThis() {
        return this;
    }
}

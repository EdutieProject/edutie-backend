package com.edutie.backend.application.learning.studyprogram.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
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
public final class SegmentsForStudentFromLessonQuery extends StudentAction<SegmentsForStudentFromLessonQuery> {
    private @NonNull LessonId lessonId;

    @Override
    protected SegmentsForStudentFromLessonQuery getThis() {
        return this;
    }
}

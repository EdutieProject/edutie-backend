package com.edutie.backend.application.learning.segment.queries;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import lombok.NonNull;

public record SegmentsForStudentFromLessonQuery(
        @NonNull UserId studentUserId,
        @NonNull LessonId lessonId
) {
}

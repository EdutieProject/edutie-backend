package com.edutie.backend.application.learning.segment.queries;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record SegmentsForStudentFromLessonQuery(
        UserId studentUserId,
        LessonId lessonId
) {
}

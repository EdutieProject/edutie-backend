package com.edutie.backend.application.learning.segment.queries;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record SegmentsForStudentFromLessonQuery(
        StudentId studentId,
        LessonId lessonId
) {
}

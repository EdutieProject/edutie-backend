package com.edutie.backend.application.creation.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record MoveSegmentCommand(
        EducatorId educatorId,
        LessonSegmentId lessonSegmentId,
        LessonSegmentId previousLessonSegmentId,
        LessonSegmentId nextLessonSegmentId
) {
}

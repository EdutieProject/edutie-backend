package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record RemoveSegmentCommand(
        EducatorId educatorId,
        LessonSegmentId segmentId
) {
}

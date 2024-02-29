package com.edutie.backend.application.creator.segment.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record RemoveLessonSegmentCommand(
        CreatorId creatorId,
        LessonSegmentId segmentId
) {
}

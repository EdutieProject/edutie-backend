package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;

public record ModifySegmentCommand(
        EducatorId educatorId,
        SegmentId segmentId,
        SegmentId previousSegmentId,
        SegmentId nextSegmentId
) {
}

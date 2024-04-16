package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.NonNull;

public record RemoveSegmentCommand(
        @NonNull UserId educatorUserId,
        @NonNull SegmentId segmentId
) {
}

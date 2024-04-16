package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.NonNull;

public record ModifySegmentCommand(
        @NonNull UserId educatorUserId,
        @NonNull SegmentId segmentId,
        String name,
//TODO: change after first segment concept
//        String overviewDescription,
//        String exerciseDescription,
        ExerciseTypeId exerciseTypeId,
        SegmentId previousSegmentId,
        SegmentId nextSegmentId
) {
}

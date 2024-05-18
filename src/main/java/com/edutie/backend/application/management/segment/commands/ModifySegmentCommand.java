package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
public final class ModifySegmentCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull SegmentId segmentId;
    private String segmentName;
    private ExerciseTypeId segmentExerciseTypeId;
    private SegmentId previousSegmentId;
    private SegmentId nextSegmentId;
}

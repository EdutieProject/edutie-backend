package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public final class CreateSegmentCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull String segmentName;
    private String segmentOverviewDescription;
    private String segmentExerciseDescription;
    private ExerciseTypeId exerciseTypeId;
    private @NonNull SegmentId previousSegmentId;
    private SegmentId nextSegmentId;
}

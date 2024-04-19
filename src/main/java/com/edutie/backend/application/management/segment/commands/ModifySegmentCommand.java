package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public final class ModifySegmentCommand {
    private @NonNull UserId educatorUserId;
    private @NonNull SegmentId segmentId;
    private String name;
    private ExerciseTypeId exerciseTypeId;
    private SegmentId previousSegmentId;
    private SegmentId nextSegmentId;
}

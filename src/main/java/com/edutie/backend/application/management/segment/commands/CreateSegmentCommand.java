package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.NonNull;

//TODO: add javadocs to commands and queries
public record CreateSegmentCommand(
        @NonNull UserId educatorUserId,
        @NonNull String segmentName,
        String segmentOverviewDescription,
        String segmentExerciseDescription,
        ExerciseTypeId exerciseTypeId,
        @NonNull SegmentId previousSegmentId,
        SegmentId nextSegmentId
) {
}

package com.edutie.backend.application.creator.segment.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record CreateLessonSegmentInBetweenCommand(
        CreatorId creatorId,
        String segmentName,
        String segmentOverviewDescription,
        String segmentExerciseDescription,
        ExerciseTypeId exerciseTypeId,
        LessonSegmentId previousSegmentId,
        LessonSegmentId nextSegmentId
) {
}

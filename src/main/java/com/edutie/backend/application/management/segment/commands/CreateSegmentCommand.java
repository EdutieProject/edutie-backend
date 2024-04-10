package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
//TODO: add javadocs to commands and queries
public record CreateSegmentCommand(
        EducatorId educatorId,
        String segmentName,
        String segmentOverviewDescription,
        String segmentExerciseDescription,
        ExerciseTypeId exerciseTypeId,
        LessonSegmentId previousSegmentId,
        LessonSegmentId nextSegmentId
) {
}

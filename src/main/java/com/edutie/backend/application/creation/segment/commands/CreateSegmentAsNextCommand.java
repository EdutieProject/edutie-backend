package com.edutie.backend.application.creation.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record CreateSegmentAsNextCommand(
        EducatorId educatorId,
        String segmentName,
        String segmentOverviewDescription,
        String segmentExerciseDescription,
        ExerciseTypeId exerciseTypeId,
        LessonSegmentId previousSegmentId
) {
}

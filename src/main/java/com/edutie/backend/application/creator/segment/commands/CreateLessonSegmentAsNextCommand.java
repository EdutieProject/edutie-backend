package com.edutie.backend.application.creator.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record CreateLessonSegmentAsNextCommand(
        EducatorId educatorId,
        String segmentName,
        String segmentOverviewDescription,
        String segmentExerciseDescription,
        ExerciseTypeId exerciseTypeId,
        LessonSegmentId previousSegmentId
) {
}

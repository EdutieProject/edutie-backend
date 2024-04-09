package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public record ChangeSegmentPropertiesCommand(
        EducatorId educatorId,
        LessonSegmentId lessonId,
        String name,
        String overviewDescription,
        String exerciseDescription,
        ExerciseTypeId exerciseTypeId
) {
}

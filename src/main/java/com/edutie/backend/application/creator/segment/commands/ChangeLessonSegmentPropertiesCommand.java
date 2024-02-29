package com.edutie.backend.application.creator.segment.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record ChangeLessonSegmentPropertiesCommand(
        CreatorId creatorId,
        LessonId lessonId,
        String name,
        String overviewDescription,
        String exerciseDescription,
        ExerciseTypeId exerciseTypeId
) {
}

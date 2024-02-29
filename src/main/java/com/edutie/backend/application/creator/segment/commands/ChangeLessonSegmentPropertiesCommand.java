package com.edutie.backend.application.creator.segment.commands;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record ChangeLessonSegmentPropertiesCommand(
        EducatorId educatorId,
        LessonId lessonId,
        String name,
        String overviewDescription,
        String exerciseDescription,
        ExerciseTypeId exerciseTypeId
) {
}

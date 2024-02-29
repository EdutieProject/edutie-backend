package com.edutie.backend.application.creator.lesson.commands;

import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

public record CreateLessonAsNextCommand(
        CreatorId creatorId,
        String lessonName,
        String lessonDescription,
        LessonId previousLessonId
) {
}
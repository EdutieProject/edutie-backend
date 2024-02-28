package com.edutie.backend.application.learning.segment.viewmodels;

import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;

public record LessonSegmentView(
        LessonSegment lessonSegment,
        boolean done
        ) {
}

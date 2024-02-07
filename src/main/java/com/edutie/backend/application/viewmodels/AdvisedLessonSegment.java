package com.edutie.backend.application.viewmodels;

import com.edutie.backend.domain.core.lessonsegment.LessonSegment;

/**
 * A class wrapping around LessonSegment together with a parameter
 * indicating difficulty calculated separately for each student.
 */
public class AdvisedLessonSegment {
    LessonSegment lessonSegment;
    Double difficultyIndicator;
}

package com.edutie.edutiebackend.application.viewmodels;

import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;

/**
 * A class wrapping around LessonSegment together with a parameter
 * indicating difficulty calculated separately for each student.
 */
public class AdvisedLessonSegment {
    LessonSegment lessonSegment;
    Double difficultyIndicator;
}

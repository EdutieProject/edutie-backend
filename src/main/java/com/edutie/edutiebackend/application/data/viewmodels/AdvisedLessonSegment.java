package com.edutie.edutiebackend.application.data.viewmodels;

import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;

/**
 * A class wrapping around LessonSegment together with a parameter
 * indicating difficulty calculated separately for each student.
 */
//TODO: incorporate into domain?
public class AdvisedLessonSegment {
    LessonSegment lessonSegment;
    Double difficultyIndicator;
}

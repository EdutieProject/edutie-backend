package com.edutie.edutiebackend.application.utils.assessment;

import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.LearningReport;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.student.Student;

public class AssessmentUtils {
    /**
     * Assesses exercise based on given Lesson Segment and a report
     * from the given exercise. The assessment omits the corresponding
     * learning resource because it focuses on the base principles of
     * the topic associated with given Lesson Segment

     */
    public static LearningResult createResult(final LessonSegment lessonSegment, final LearningReport learningReport)
    {
        //TODO!
    }
    /**
     * Applies learning result to student entity

     */
    public static Double applyResult(Student student, final LearningResult learningResult )
    {
        //TODO!
    }
}

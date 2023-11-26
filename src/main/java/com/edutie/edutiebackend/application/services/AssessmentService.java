package com.edutie.edutiebackend.application.services;

import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.LearningReport;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.student.Student;

/**
 * Interface responsible for
 * assessment.
 */
public interface AssessmentService {
    /**
     * Assesses exercise based on given Lesson Segment and a report
     * from the given exercise. The assessment omits the corresponding
     * learning resource because it focuses on the base principles of
     * the topic associated with given Lesson Segment
     * @param exerciseReport report from the exercise as user input
     * @param lessonSegment corresponding lesson segment
     * @return assessment as LearningResult
     */
    LearningResult createResult(LearningReport exerciseReport, final LessonSegment lessonSegment);

    /**
     * Adapts student's learning parameters, applying the given learning Result.
     * @param student student to adapt.
     * @param learningResult learning result to apply
     * @return The total value of points that are changed. For example, if
     * method causes +5.5 in one trait mapping and -2.5 in other trait mapping,
     * the return value is 3.0
     */
    double adaptStudentParameters(Student student, final LearningResult learningResult);

}

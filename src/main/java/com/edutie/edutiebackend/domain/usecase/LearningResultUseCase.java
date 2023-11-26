package com.edutie.edutiebackend.domain.usecase;

import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.LearningReport;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.student.Student;

public interface LearningResultUseCase {
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
     * Applies learning result to student entity
     * @param learningResult learning result to apply
     * @param student student to modify
     * @return The difference made to all learning param trackers as double
     */
    double applyResult(final LearningResult learningResult, Student student);
}

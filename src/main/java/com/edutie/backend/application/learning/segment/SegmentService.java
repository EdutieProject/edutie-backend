package com.edutie.backend.application.learning.segment;

import com.edutie.backend.application.learning.segment.viewmodels.SegmentView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

import java.util.List;

/**
 * Service interface for managing segments in the educational learning context.
 */
public interface SegmentService {

    /**
     * Retrieves a list of segment views for a specific lesson and student.
     *
     * @param lessonId  The identifier of the lesson.
     * @param studentId The identifier of the student.
     * @return A list of segment views for the specified lesson and student.
     */
    List<SegmentView> getSegmentsOfLessonForStudent(LessonId lessonId, StudentId studentId);
}

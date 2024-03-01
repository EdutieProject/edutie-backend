package com.edutie.backend.application.learning.lesson;

import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

import java.util.List;

/**
 * Service interface for managing lessons in the educational learning context.
 */
public interface LessonService {

    /**
     * Retrieves a list of lesson views for a specific course and student.
     *
     * @param courseId  The identifier of the course.
     * @param studentId The identifier of the student.
     * @return A list of lesson views for the specified course and student.
     */
    List<LessonView> getLessonsOfCourseForStudent(CourseId courseId, StudentId studentId);
}

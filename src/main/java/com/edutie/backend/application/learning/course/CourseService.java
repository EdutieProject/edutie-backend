package com.edutie.backend.application.learning.course;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

import java.util.List;

/**
 * Service interface for managing courses in the educational system.
 */
public interface CourseService {

    /**
     * Retrieves a list of courses associated with the specified science.
     *
     * @param scienceId The identifier of the science.
     * @return A list of courses related to the specified science.
     */
    List<Course> getCoursesOfScience(ScienceId scienceId);

    /**
     * Retrieves a list of courses in progress for the specified student.
     *
     * @param studentId The identifier of the student.
     * @return A list of courses in progress for the specified student.
     */
    List<Course> getCoursesInProgressOfStudent(StudentId studentId);
}

package com.edutie.backend.application.learning.course;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesOfScience(ScienceId scienceId);
    List<Course> getCoursesInProgressOfStudent(StudentId studentId);
}

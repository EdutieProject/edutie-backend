package com.edutie.backend.application.learning.lesson.queries;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

public record LessonsForStudentFromCourseQuery(CourseId courseId, StudentId studentId) {
}

package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.learner.student.identities.StudentId;

public record CoursesByStudentProgressQuery(StudentId studentId) {
}

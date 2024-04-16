package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.administration.UserId;

public record CoursesByStudentProgressQuery(UserId studentUserId) {
}

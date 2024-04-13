package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.common.identities.UserId;

public record CoursesByStudentProgressQuery(UserId studentUserId) {
}

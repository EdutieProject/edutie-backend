package com.edutie.backend.application.learning.course.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record CoursesByStudentProgressQuery(
        @NonNull UserId studentUserId
) {
}

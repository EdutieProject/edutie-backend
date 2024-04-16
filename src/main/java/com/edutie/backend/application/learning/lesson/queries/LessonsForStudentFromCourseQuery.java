package com.edutie.backend.application.learning.lesson.queries;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.NonNull;

public record LessonsForStudentFromCourseQuery(
        @NonNull CourseId courseId,
        @NonNull UserId studentUserId) {
}

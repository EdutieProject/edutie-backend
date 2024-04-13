package com.edutie.backend.application.learning.lesson.queries;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

public record LessonsForStudentFromCourseQuery(CourseId courseId, UserId studentUserId) {
}

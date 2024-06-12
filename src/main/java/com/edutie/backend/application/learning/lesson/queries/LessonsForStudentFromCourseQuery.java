package com.edutie.backend.application.learning.lesson.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import lombok.NonNull;

import java.util.Objects;

public final class LessonsForStudentFromCourseQuery extends StudentAction<LessonsForStudentFromCourseQuery> {
    private @NonNull CourseId courseId;
    private @NonNull UserId studentUserId;

    @Override
    protected LessonsForStudentFromCourseQuery getThis() {
        return this;
    }
}

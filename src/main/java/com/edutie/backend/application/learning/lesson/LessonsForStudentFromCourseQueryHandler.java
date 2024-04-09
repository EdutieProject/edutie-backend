package com.edutie.backend.application.learning.lesson;

import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.application.shared.UseCaseHandler;
import validation.WrapperResult;

import java.util.List;

public interface LessonsForStudentFromCourseQueryHandler
        extends UseCaseHandler<WrapperResult<List<LessonView>>, LessonsForStudentFromCourseQuery> {
}

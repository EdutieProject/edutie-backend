package com.edutie.backend.application.learning.studyprogram;

import com.edutie.backend.application.learning.studyprogram.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.WrapperResult;

import java.util.List;

public interface LessonsForStudentFromCourseQueryHandler
        extends UseCaseHandler<WrapperResult<List<LessonView>>, LessonsForStudentFromCourseQuery> {
}

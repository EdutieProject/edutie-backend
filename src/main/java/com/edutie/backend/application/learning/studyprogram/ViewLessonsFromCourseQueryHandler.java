package com.edutie.backend.application.learning.studyprogram;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewLessonsFromCourseQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import validation.WrapperResult;

import java.util.List;

public interface ViewLessonsFromCourseQueryHandler extends UseCaseHandler<WrapperResult<List<LessonView>>, ViewLessonsFromCourseQuery> { }

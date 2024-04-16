package com.edutie.backend.application.management.lesson;

import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

import java.util.List;

public interface CreatedLessonsQueryHandler
        extends UseCaseHandler<WrapperResult<List<Lesson>>, CreatedLessonsQuery> {
}

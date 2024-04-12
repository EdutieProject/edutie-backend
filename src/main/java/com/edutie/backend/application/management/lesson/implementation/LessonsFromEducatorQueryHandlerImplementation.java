package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.LessonsFromEducatorQueryHandler;
import com.edutie.backend.application.management.lesson.queries.LessonsFromEducatorQuery;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

import java.util.List;

public class LessonsFromEducatorQueryHandlerImplementation extends HandlerBase implements LessonsFromEducatorQueryHandler {
    @Override
    public WrapperResult<List<Lesson>> handle(LessonsFromEducatorQuery lessonsFromEducatorQuery) {
        return null;
    }
}

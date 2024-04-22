package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreatedLessonsQueryHandler;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreatedLessonsQueryHandlerImplementation extends HandlerBase implements CreatedLessonsQueryHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<List<Lesson>> handle(CreatedLessonsQuery query) {
        Educator educator = educatorPersistence.getByUserId(query.educatorUserId());
        return lessonPersistence.getAllOfEducatorId(educator.getId());
    }
}

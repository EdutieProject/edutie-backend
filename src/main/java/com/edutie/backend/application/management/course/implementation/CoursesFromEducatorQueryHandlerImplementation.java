package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CoursesFromEducatorQueryHandler;
import com.edutie.backend.application.management.course.queries.CoursesFromEducatorQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
@Component
@RequiredArgsConstructor
public class CoursesFromEducatorQueryHandlerImplementation extends HandlerBase implements CoursesFromEducatorQueryHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesFromEducatorQuery query) {
        Educator educator = educatorPersistence.getByUserId(query.educatorUserId());
        return WrapperResult.successWrapper(coursePersistence.getAllOfEducatorId(educator.getId()));
    }
}

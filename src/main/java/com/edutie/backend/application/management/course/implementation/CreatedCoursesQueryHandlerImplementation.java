package com.edutie.backend.application.management.course.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.CreatedCoursesQueryHandler;
import com.edutie.backend.application.management.course.queries.CreatedCoursesQuery;
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
public class CreatedCoursesQueryHandlerImplementation extends HandlerBase implements CreatedCoursesQueryHandler {
    private final CoursePersistence coursePersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<List<Course>> handle(CreatedCoursesQuery query) {
        LOGGER.info("Retrieving courses created by user of id {}", query.educatorUserId().identifierValue());
        Educator educator = educatorPersistence.getByAuthorizedUserId(query.educatorUserId());
        return coursePersistence.getAllOfEducatorId(educator.getId());
    }
}

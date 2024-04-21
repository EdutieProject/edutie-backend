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
        Educator educator = educatorPersistence.getByUserId(query.educatorUserId());
        WrapperResult<List<Course>> coursesResult = coursePersistence.getAllOfEducatorId(educator.getId());
        if (coursesResult.isFailure())
            return coursesResult;
        return WrapperResult.successWrapper(coursesResult.getValue());
    }
}

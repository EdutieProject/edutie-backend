package com.edutie.backend.application.learning.course.implementation;

import com.edutie.backend.application.learning.course.CoursesByScienceQueryHandler;
import com.edutie.backend.application.learning.course.queries.CoursesByScienceQuery;
import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CoursesByScienceQueryHandlerImplementation extends HandlerBase implements CoursesByScienceQueryHandler {
    private final CoursePersistence coursePersistence;
    @Override
    public WrapperResult<List<Course>> handle(CoursesByScienceQuery query) {
        LOGGER.info("Retrieving all courses of science of id {}", query.scienceId().identifierValue());
        WrapperResult<List<Course>> coursesResult = coursePersistence.getAllOfScienceId(query.scienceId());
        if (coursesResult.isFailure())
            return coursesResult;
        return WrapperResult.successWrapper(coursesResult.getValue());
    }
}

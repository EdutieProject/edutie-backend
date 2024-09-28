package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.CoursesByScienceQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.CoursesByScienceQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoursesByScienceQueryHandlerImplementation extends HandlerBase implements CoursesByScienceQueryHandler {
	private final CoursePersistence coursePersistence;

	@Override
	public WrapperResult<List<Course>> handle(CoursesByScienceQuery query) {
		log.info("Retrieving all courses of science of id {}", query.scienceId().identifierValue());
		WrapperResult<List<Course>> coursesResult = coursePersistence.getAllOfScienceId(query.scienceId());
		if (coursesResult.isFailure()) {
			log.info("Persistence error: {}", coursesResult.getError().toString());
			return coursesResult;
		}
		log.info("Courses retrieved successfully");
		return WrapperResult.successWrapper(coursesResult.getValue());
	}
}

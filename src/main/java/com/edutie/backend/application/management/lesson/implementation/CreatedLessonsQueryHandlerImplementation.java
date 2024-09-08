package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreatedLessonsQueryHandler;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatedLessonsQueryHandlerImplementation extends HandlerBase implements CreatedLessonsQueryHandler {
	private final LessonPersistence lessonPersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	public WrapperResult<List<Lesson>> handle(CreatedLessonsQuery query) {
		log.info("Retrieving lessons made by educator {}", query.educatorUserId().identifierValue());
		Educator educator = educatorPersistence.getByAuthorizedUserId(query.educatorUserId());
		return lessonPersistence.getAllOfEducatorId(educator.getId());
	}
}

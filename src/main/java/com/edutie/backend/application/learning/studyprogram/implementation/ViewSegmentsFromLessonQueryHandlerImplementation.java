package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.ViewSegmentsFromLessonQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewSegmentsFromLessonQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domainservice.common.logging.ExternalFailureLog;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewSegmentsFromLessonQueryHandlerImplementation extends HandlerBase implements ViewSegmentsFromLessonQueryHandler {
	private final StudentPersistence studentPersistence;
	private final LessonPersistence lessonPersistence;

	@Override
	public WrapperResult<List<SegmentView>> handle(ViewSegmentsFromLessonQuery query) {
		log.info("Retrieving segments from lesson of id {} for student of id {}", query.lessonId().identifierValue(), query.studentUserId().identifierValue());
		WrapperResult<Lesson> lessonWrapperResult = lessonPersistence.getById(query.lessonId());
		if (lessonWrapperResult.isFailure()) {
			return ExternalFailureLog.persistenceFailure(lessonWrapperResult, log).map(o -> null);
		}
		Lesson lesson = lessonWrapperResult.getValue();
		return WrapperResult.successWrapper(lesson.getSegments().stream().map(o -> new SegmentView(o, 2, 1, false)).collect(Collectors.toList()));
	}
}

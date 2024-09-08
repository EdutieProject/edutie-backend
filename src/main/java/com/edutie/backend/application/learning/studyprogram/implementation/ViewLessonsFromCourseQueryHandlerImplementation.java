package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.ViewLessonsFromCourseQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewLessonsFromCourseQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewLessonsFromCourseQueryHandlerImplementation extends HandlerBase implements ViewLessonsFromCourseQueryHandler {
	private final LessonPersistence lessonPersistence;
	private final StudentPersistence studentPersistence;

	@Override
	public WrapperResult<List<LessonView>> handle(ViewLessonsFromCourseQuery query) {
		log.info("Retrieving lessons for course of id {} for student of id {}", query.courseId().identifierValue(), query.studentUserId().identifierValue());
		Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
		WrapperResult<List<Lesson>> lessonsResult = lessonPersistence.getAllOfCourseId(query.courseId());
		if (lessonsResult.isFailure()) {
			log.info("Persistence error: {}", lessonsResult.getError().toString());
			return lessonsResult.map(o -> null);
		}

		//TODO: note that current implementation signs which lesson is "touched".
		return lessonsResult.map(primaryResult -> primaryResult.stream().map(o -> new LessonView(o, false)).collect(Collectors.toList()));
	}
}

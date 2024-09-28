package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.ModifyLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.ModifyLessonCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import validation.Result;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ModifyLessonCommandHandlerImplementation extends HandlerBase implements ModifyLessonCommandHandler {
	private final LessonPersistence lessonPersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	@Transactional
	public Result handle(ModifyLessonCommand command) {
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Lesson lesson = lessonPersistence.getById(command.lessonId()).getValue();
		if (!educator.isAuthorOf(lesson)) {
			log.info("Educator has insufficient permissions to modify this lesson");
			return Result.failure(EducationError.educatorMustBeAuthorError(Lesson.class));
		}
		if (command.lessonName() != null)
			lesson.setName(command.lessonName());
		if (command.lessonDescription() != null)
			lesson.setDescription(command.lessonDescription());
		if (command.previousLessonId() != null) {
			Lesson previousLesson = lessonPersistence.getById(command.previousLessonId()).getValue();
			lesson.setPreviousElement(previousLesson);
		}
		for (LessonId nextLessonId: command.nextLessonIds()) {
			Lesson nextLesson = lessonPersistence.getById(nextLessonId).getValue();
			lesson.setPreviousElement(nextLesson);
		}
		lessonPersistence.save(lesson).throwIfFailure();
		return WrapperResult.successWrapper(lesson);
	}
}

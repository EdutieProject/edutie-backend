package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domainservice.studyprogram.initializers.lesson.LessonInitializer;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
	private final LessonPersistence lessonPersistence;
	private final EducatorPersistence educatorPersistence;

	private final LessonInitializer lessonInitializer;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public WrapperResult<Lesson> handle(CreateLessonCommand command) {
		log.info("Creating lesson by educator of id {} with previous lesson id {}", command.educatorUserId().identifierValue(), command.previousLessonId().identifierValue());
		Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
		Lesson previousLesson = lessonPersistence.getById(command.previousLessonId()).getValue();
		Lesson lesson = Lesson.create(educator, previousLesson);
		lesson.setName(command.lessonName());
		lesson.setDescription(command.lessonDescription() != null ? command.lessonDescription() : "");
		lessonPersistence.save(lesson).throwIfFailure();
		lessonInitializer.initializeLesson(lesson).throwIfFailure();
		if (command.nextLessonId() == null) {
			log.info("Next lesson not specified. Lesson successfully created as the learning tree leaf.");
			return WrapperResult.successWrapper(lesson);
		}
		Lesson nextLesson = lessonPersistence.getById(command.nextLessonId()).getValue();
		nextLesson.setPreviousElement(lesson);
		lessonPersistence.save(nextLesson).throwIfFailure();
		lesson.addNextElement(nextLesson);
		log.info("Created new lesson successfully in between lesson of id {} and lesson of id {}", previousLesson.getId().identifierValue(), nextLesson.getId().identifierValue());
		return WrapperResult.successWrapper(lesson);
	}
}

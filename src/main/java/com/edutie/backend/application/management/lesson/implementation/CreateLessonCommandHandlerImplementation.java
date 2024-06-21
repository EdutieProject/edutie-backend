package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.services.studyprogram.initializers.lesson.LessonInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;

    private final LessonInitializer lessonInitializer;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WrapperResult<Lesson> handle(CreateLessonCommand command) {
        LOGGER.info("Creating lesson by educator of id {} with previous lesson id {}",
                command.educatorUserId().identifierValue(),
                command.previousLessonId().identifierValue());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        WrapperResult<Lesson> previousLessonWrapperResult = lessonPersistence.getById(command.previousLessonId());
        if (previousLessonWrapperResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(previousLessonWrapperResult, LOGGER);
        }
        Lesson previousLesson = previousLessonWrapperResult.getValue();
        Lesson lesson = Lesson.create(educator, previousLesson);
        lesson.setPreviousElement(previousLesson);
        lesson.setName(command.lessonName());
        lesson.setDescription(command.lessonDescription() != null ? command.lessonDescription() : "");
        Result saveResult = lessonPersistence.save(lesson);
        if (saveResult.isFailure())
            return ExternalFailureLog.persistenceFailure(saveResult, LOGGER).map(() -> null);
        Result lessonInitialization = lessonInitializer.initializeLesson(lesson);
        if (lessonInitialization.isFailure()) {
            LOGGER.warn("Lesson initialization failure");
            return lessonInitialization.map(() -> null);
        }
        if (command.nextLessonId() == null) {
            LOGGER.info("Next lesson not specified. Lesson successfully created as the learning tree leaf.");
            return WrapperResult.successWrapper(lesson);
        }
        WrapperResult<Lesson> nextLessonWrapperResult = lessonPersistence.getById(command.nextLessonId());
        if (nextLessonWrapperResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(nextLessonWrapperResult, LOGGER);
        }
        Lesson nextLesson = nextLessonWrapperResult.getValue();
        nextLesson.setPreviousElement(lesson);
        Result saveResult2 = lessonPersistence.save(nextLesson);
        lesson.addNextElement(nextLesson);
        if (saveResult2.isFailure()) {
            return ExternalFailureLog.persistenceFailure(saveResult2, LOGGER).map(() -> null);
        }
        LOGGER.info("Created new lesson successfully in between lesson of id "
                + previousLesson.getId().identifierValue() + " and lesson of id "
                + nextLesson.getId().identifierValue());
        return WrapperResult.successWrapper(lesson);
    }
}

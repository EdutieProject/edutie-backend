package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domainservice.studyprogram.initializers.lesson.LessonInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
        Lesson previousLesson = lessonPersistence.getById(command.previousLessonId()).getValue();
        Lesson lesson = Lesson.create(educator, previousLesson);
        lesson.setName(command.lessonName());
        lesson.setDescription(command.lessonDescription() != null ? command.lessonDescription() : "");
        lessonPersistence.save(lesson).throwIfFailure();
        lessonInitializer.initializeLesson(lesson).throwIfFailure();
        if (command.nextLessonId() == null) {
            LOGGER.info("Next lesson not specified. Lesson successfully created as the learning tree leaf.");
            return WrapperResult.successWrapper(lesson);
        }
        Lesson nextLesson = lessonPersistence.getById(command.nextLessonId()).getValue();
        nextLesson.setPreviousElement(lesson);
        lessonPersistence.save(nextLesson).throwIfFailure();
        lesson.addNextElement(nextLesson);
        LOGGER.info("Created new lesson successfully in between lesson of id "
                + previousLesson.getId().identifierValue() + " and lesson of id "
                + nextLesson.getId().identifierValue());
        return WrapperResult.successWrapper(lesson);
    }
}

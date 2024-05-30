package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<Lesson> handle(CreateLessonCommand command) {
        LOGGER.info("Creating lesson by educator of id {} with previous lesson id {}",
                command.educatorUserId().identifierValue(),
                command.previousLessonId().identifierValue());
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Lesson> previousLessonWrapperResult = lessonPersistence.getById(command.previousLessonId());
        if (previousLessonWrapperResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + previousLessonWrapperResult.getError().toString());
            return previousLessonWrapperResult;
        }
        Lesson previousLesson = previousLessonWrapperResult.getValue();
        Lesson lesson = Lesson.create(educator, previousLesson.getCourse());
        lesson.setPreviousElement(previousLesson);
        lesson.setName(command.lessonName());
        lesson.setDescription(command.lessonDescription() != null ? command.lessonDescription() : "");
        Result saveResult = lessonPersistence.save(lesson);
        if (saveResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + saveResult.getError().toString());
            return saveResult.map(() -> null);
        }
        if (command.nextLessonId() == null) {
            LOGGER.info("Next lesson not specified. Lesson successfully created as the learning tree leaf.");
            return WrapperResult.successWrapper(lesson);
        }
        WrapperResult<Lesson> nextLessonWrapperResult = lessonPersistence.getById(command.nextLessonId());
        if (nextLessonWrapperResult.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + nextLessonWrapperResult.getError().toString());
            return nextLessonWrapperResult;
        }
        Lesson nextLesson = nextLessonWrapperResult.getValue();
        nextLesson.setPreviousElement(lesson);
        Result saveResult2 = lessonPersistence.save(nextLesson);
        if (saveResult2.isFailure()) {
            LOGGER.info("Persistence error occurred. Error: " + saveResult2.getError().toString());
            return saveResult2.map(()->null);
        }
        LOGGER.info("Created lesson  successfully in between lesson of id "
                + previousLesson.getId().identifierValue() + " and lesson of id "
                + nextLesson.getId().identifierValue());
        return WrapperResult.successWrapper(lesson);
    }
}

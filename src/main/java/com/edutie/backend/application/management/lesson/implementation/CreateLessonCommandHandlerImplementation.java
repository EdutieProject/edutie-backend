package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import com.edutie.backend.services.studyprogram.creators.lesson.LessonCreationDetails;
import com.edutie.backend.services.studyprogram.creators.lesson.LessonCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;
    private final LessonCreator lessonCreator;

    @Override
    public WrapperResult<Lesson> handle(CreateLessonCommand command) {
        LOGGER.info("Creating lesson by educator of id {} with previous lesson id {}",
                command.educatorUserId().identifierValue(),
                command.previousLessonId().identifierValue());

        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Lesson> previousLessonWrapperResult = lessonPersistence.getById(command.previousLessonId());
        if (previousLessonWrapperResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(previousLessonWrapperResult, LOGGER);
        }
        LessonCreationDetails creationDetails = new LessonCreationDetails()
                .educator(educator)
                .name(command.lessonName())
                .description(command.lessonDescription())
                .previousLesson(previousLessonWrapperResult.getValue());
        if (command.nextLessonId() == null)
            return lessonCreator.createLesson(creationDetails);

        WrapperResult<Lesson> nextLessonWrapperResult = lessonPersistence.getById(command.nextLessonId());
        if (nextLessonWrapperResult.isFailure())
            return ExternalFailureLog.persistenceFailure(nextLessonWrapperResult, LOGGER).map(() -> null);

        return lessonCreator.createLesson(
                creationDetails.nextLesson(nextLessonWrapperResult.getValue())
        );
    }
}

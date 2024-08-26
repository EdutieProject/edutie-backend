package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.domain.education.EducationError;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RemoveLessonCommandHandlerImplementation extends HandlerBase implements RemoveLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result handle(RemoveLessonCommand command) {
        LOGGER.info("Removing lesson of id {} by user of id {}", command.lessonId(), command.educatorUserId());
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        Lesson lesson = lessonPersistence.getById(command.lessonId()).getValue();
        if (!educator.isAuthorOf(lesson)) {
            LOGGER.info("Insufficient permissions to remove this lesson");
            return Result.failure(EducationError.educatorMustBeAuthorError(Lesson.class));
        }
        Lesson previousLesson = lesson.getPreviousElement();
        Set<Lesson> nextLessons = lesson.getNextElements();
        for (Lesson nextLesson : nextLessons) {
            LOGGER.info("Bonding next lesson of id " + nextLesson.getId().identifierValue()
                    + " to the previous lesson of id" + previousLesson.getId().identifierValue());
            nextLesson.setPreviousElement(previousLesson);
            lessonPersistence.save(nextLesson);
        }
        if (command.removeSegments()) {
            lessonPersistence.deepRemove(lesson).throwIfFailure();
        } else {
            lessonPersistence.remove(lesson).throwIfFailure();
        }
        LOGGER.info("Lesson removed successfully");
        return Result.success();
    }
}

package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.ModifyLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.ModifyLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.errors.EducatorError;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifyLessonCommandHandlerImplementation extends HandlerBase implements ModifyLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(ModifyLessonCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Lesson> lessonWrapperResult = lessonPersistence.getById(command.lessonId());
        if (lessonWrapperResult.isFailure())
            return lessonWrapperResult;
        Lesson lesson = lessonWrapperResult.getValue();
        if(!lesson.getEducator().equals(educator)) {
            LOGGER.info("Educator has insufficient permissions to modify this lesson");
            return Result.failure(EducatorError.mustBeOwnerError(Lesson.class));
        }

        if (command.lessonName() != null)
            lesson.setName(command.lessonName());
        if (command.lessonDescription() != null)
            lesson.setDescription(command.lessonDescription());
        if (command.previousLessonId() != null) {
            WrapperResult<Lesson> prevLessonWrapper = lessonPersistence.getById(command.previousLessonId());
            if (prevLessonWrapper.isFailure())
                return prevLessonWrapper;
            lesson.setPreviousElement(prevLessonWrapper.getValue());
        }
        for (LessonId nextLessonId : command.nextLessonIds()) {
            WrapperResult<Lesson> nextLessonWrapper = lessonPersistence.getById(nextLessonId);
            if (nextLessonWrapper.isFailure())
                return nextLessonWrapper;
            lesson.setPreviousElement(nextLessonWrapper.getValue());
        }
        lesson.update(command.educatorUserId());
        lessonPersistence.save(lesson);
        return WrapperResult.successWrapper(lesson);
    }
}

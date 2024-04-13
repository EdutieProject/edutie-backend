package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RemoveLessonCommandHandlerImplementation extends HandlerBase implements RemoveLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(RemoveLessonCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Lesson> lessonWrapperResult = lessonPersistence.getById(command.lessonId());
        if (lessonWrapperResult.isFailure())
            return lessonWrapperResult;
        Lesson lesson = lessonWrapperResult.getValue();
        if (!lesson.getEducator().equals(educator))
            return Result.failure(new Error("LESSON-1", "To modify lesson u must be a creator of it"));
        Lesson previousLesson = lesson.getPreviousElement();
        Set<Lesson> nextLessons = lesson.getNextElements();
        for (Lesson nextLesson : nextLessons) {
            nextLesson.setPreviousElement(previousLesson);
            lessonPersistence.save(nextLesson);
        }
        lessonPersistence.remove(lesson);
        return Result.success();
    }
}

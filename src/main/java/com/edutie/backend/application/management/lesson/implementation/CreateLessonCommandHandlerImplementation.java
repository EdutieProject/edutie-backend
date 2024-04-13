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
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
    private final LessonPersistence lessonPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<Lesson> handle(CreateLessonCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Lesson> previousLessonWrapperResult = lessonPersistence.getById(command.previousLessonId());
        if (previousLessonWrapperResult.isFailure())
            return previousLessonWrapperResult;
        Lesson previousLesson = previousLessonWrapperResult.getValue();
        Lesson lesson = Lesson.create(educator, previousLesson.getCourse());
        lesson.setPreviousElement(previousLesson);
        lesson.setName(command.lessonName());
        lesson.setDescription(command.lessonDescription() != null ? command.lessonDescription() : "");
        lessonPersistence.save(lesson);
        if (command.nextLessonId() == null)
            return WrapperResult.successWrapper(lesson);
        WrapperResult<Lesson> nextLessonWrapperResult = lessonPersistence.getById(command.nextLessonId());
        if (nextLessonWrapperResult.isFailure())
            return nextLessonWrapperResult;
        Lesson nextLesson = nextLessonWrapperResult.getValue();
        nextLesson.setPreviousElement(lesson);
        lessonPersistence.save(nextLesson);
        return WrapperResult.successWrapper(lesson);
    }
}

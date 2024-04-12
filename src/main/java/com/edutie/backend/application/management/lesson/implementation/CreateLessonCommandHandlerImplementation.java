package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

public class CreateLessonCommandHandlerImplementation extends HandlerBase implements CreateLessonCommandHandler {
    @Override
    public WrapperResult<Lesson> handle(CreateLessonCommand createLessonCommand) {
        return null;
    }
}

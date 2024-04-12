package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import validation.Result;

public class RemoveLessonCommandHandlerImplementation extends HandlerBase implements RemoveLessonCommandHandler {
    @Override
    public Result handle(RemoveLessonCommand removeLessonCommand) {
        return null;
    }
}

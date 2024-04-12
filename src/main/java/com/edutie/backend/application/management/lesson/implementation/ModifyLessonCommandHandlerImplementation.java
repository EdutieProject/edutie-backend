package com.edutie.backend.application.management.lesson.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import validation.Result;

public class ModifyLessonCommandHandlerImplementation extends HandlerBase implements ModifyCourseCommandHandler {
    @Override
    public Result handle(ModifyCourseCommand modifyCourseCommand) {
        return null;
    }
}

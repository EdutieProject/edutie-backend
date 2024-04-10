package com.edutie.backend.application.management.lesson;

import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.Result;

public interface RemoveLessonCommandHandler
        extends UseCaseHandler<Result, RemoveLessonCommand> {
}

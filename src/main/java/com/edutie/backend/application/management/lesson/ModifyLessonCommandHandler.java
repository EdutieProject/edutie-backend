package com.edutie.backend.application.management.lesson;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.lesson.commands.ModifyLessonCommand;
import validation.Result;

public interface ModifyLessonCommandHandler extends UseCaseHandler<Result, ModifyLessonCommand> { }

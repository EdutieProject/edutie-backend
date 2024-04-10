package com.edutie.backend.application.management.course;

import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.Result;

public interface ModifyCourseCommandHandler
        extends UseCaseHandler<Result, ModifyCourseCommand> {
}

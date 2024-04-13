package com.edutie.backend.application.management.course;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import validation.Result;

public interface RemoveCourseCommandHandler extends UseCaseHandler<Result, RemoveCourseCommand> {
}

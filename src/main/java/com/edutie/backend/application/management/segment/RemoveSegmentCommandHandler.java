package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.Result;

public interface RemoveSegmentCommandHandler extends UseCaseHandler<Result, RemoveSegmentCommand> {
}

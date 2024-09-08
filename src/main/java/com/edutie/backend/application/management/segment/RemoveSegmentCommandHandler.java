package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import validation.Result;

public interface RemoveSegmentCommandHandler extends UseCaseHandler<Result, RemoveSegmentCommand> { }

package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import validation.Result;

public class RemoveSegmentCommandHandlerImplementation extends HandlerBase implements RemoveSegmentCommandHandler {
    @Override
    public Result handle(RemoveSegmentCommand removeSegmentCommand) {
        return null;
    }
}

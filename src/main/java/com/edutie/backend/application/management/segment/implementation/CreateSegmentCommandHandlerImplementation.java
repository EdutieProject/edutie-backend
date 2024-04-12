package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

public class CreateSegmentCommandHandlerImplementation extends HandlerBase implements CreateSegmentCommandHandler {
    @Override
    public WrapperResult<Segment> handle(CreateSegmentCommand createSegmentCommand) {
        return null;
    }
}

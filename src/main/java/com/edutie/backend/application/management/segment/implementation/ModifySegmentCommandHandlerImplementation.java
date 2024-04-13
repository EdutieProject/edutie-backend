package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

public class ModifySegmentCommandHandlerImplementation extends HandlerBase implements ModifySegmentCommandHandler {
    @Override
    public WrapperResult<Segment> handle(ModifySegmentCommand modifySegmentCommand) {
        return null;
    }
}

package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

public interface CreateSegmentCommandHandler
        extends UseCaseHandler<WrapperResult<Segment>, CreateSegmentCommand> {
}

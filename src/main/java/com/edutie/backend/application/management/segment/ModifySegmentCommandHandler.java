package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

public interface ModifySegmentCommandHandler extends UseCaseHandler<WrapperResult<Segment>, ModifySegmentCommand> { }

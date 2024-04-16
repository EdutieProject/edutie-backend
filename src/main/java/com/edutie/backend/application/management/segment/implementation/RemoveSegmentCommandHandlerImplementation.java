package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
@Component
@RequiredArgsConstructor
public class RemoveSegmentCommandHandlerImplementation extends HandlerBase implements RemoveSegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public Result handle(RemoveSegmentCommand removeSegmentCommand) {
        return null;
    }
}

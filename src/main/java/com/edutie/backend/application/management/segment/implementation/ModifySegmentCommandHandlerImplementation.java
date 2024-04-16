package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class ModifySegmentCommandHandlerImplementation extends HandlerBase implements ModifySegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<Segment> handle(ModifySegmentCommand modifySegmentCommand) {
        return null;
    }
}

package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.SegmentsFromEducatorQueryHandler;
import com.edutie.backend.application.management.segment.queries.SegmentsFromEducatorQuery;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

import java.util.List;

public class SegmentsFromEducatorQueryHandlerImplementation extends HandlerBase implements SegmentsFromEducatorQueryHandler {
    @Override
    public WrapperResult<List<Segment>> handle(SegmentsFromEducatorQuery segmentsFromEducatorQuery) {
        return null;
    }
}

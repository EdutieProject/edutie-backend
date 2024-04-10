package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.management.segment.queries.SegmentsFromEducatorQuery;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.lessonsegment.Segment;
import validation.WrapperResult;

import java.util.List;

public interface SegmentsFromEducatorQueryHandler
        extends UseCaseHandler<WrapperResult<List<Segment>>, SegmentsFromEducatorQuery> {
}

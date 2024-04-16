package com.edutie.backend.application.management.segment;

import com.edutie.backend.application.management.segment.queries.CreatedSegmentsQuery;
import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

import java.util.List;

public interface CreatedSegmentsQueryHandler
        extends UseCaseHandler<WrapperResult<List<Segment>>, CreatedSegmentsQuery> {
}

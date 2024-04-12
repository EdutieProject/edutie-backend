package com.edutie.backend.application.learning.segment;

import com.edutie.backend.application.learning.segment.queries.SegmentsForStudentFromLessonQuery;
import com.edutie.backend.application.learning.segment.viewmodels.SegmentView;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.WrapperResult;

import java.util.List;

public interface SegmentsForStudentFromLessonQueryHandler
        extends UseCaseHandler<WrapperResult<List<SegmentView>>, SegmentsForStudentFromLessonQuery> {
}

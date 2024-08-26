package com.edutie.backend.application.learning.studyprogram;

import com.edutie.backend.application.learning.studyprogram.queries.SegmentsForStudentFromLessonQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import com.edutie.backend.application.common.UseCaseHandler;
import validation.WrapperResult;

import java.util.List;

public interface SegmentsForStudentFromLessonQueryHandler
        extends UseCaseHandler<WrapperResult<List<SegmentView>>, SegmentsForStudentFromLessonQuery> {
}

package com.edutie.backend.application.learning.studyprogram;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewSegmentsFromLessonQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import validation.WrapperResult;

import java.util.List;

public interface ViewSegmentsFromLessonQueryHandler extends UseCaseHandler<WrapperResult<List<SegmentView>>, ViewSegmentsFromLessonQuery> { }

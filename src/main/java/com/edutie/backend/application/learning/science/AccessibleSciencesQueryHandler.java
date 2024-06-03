package com.edutie.backend.application.learning.science;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.science.queries.AccessibleSciencesQuery;
import com.edutie.backend.domain.studyprogram.science.Science;
import validation.WrapperResult;

import java.util.List;

public interface AccessibleSciencesQueryHandler extends UseCaseHandler<WrapperResult<List<Science>>, AccessibleSciencesQuery> {
}


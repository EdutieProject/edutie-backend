package com.edutie.application.profiles.student;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import validation.WrapperResult;

import java.util.List;

public interface GetLatestLearningResultsQueryHandler extends UseCaseHandler<WrapperResult<List<LearningResult>>, GetLatestLearningResultsQuery> {
}

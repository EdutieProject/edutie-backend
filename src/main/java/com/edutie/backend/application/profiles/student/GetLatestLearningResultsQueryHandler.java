package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import validation.WrapperResult;

import java.util.List;

public interface GetLatestLearningResultsQueryHandler extends UseCaseHandler<WrapperResult<List<LearningResult>>, GetLatestLearningResultsQuery> {
}

package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.queries.GetLearningResultsSolutionSubmissionQuery;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import validation.WrapperResult;

public interface GetSolutionSubmissionByIdQueryHandler extends UseCaseHandler<WrapperResult<SolutionSubmissionBase>, GetLearningResultsSolutionSubmissionQuery> {
}

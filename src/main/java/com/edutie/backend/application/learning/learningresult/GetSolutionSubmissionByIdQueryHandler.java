package com.edutie.backend.application.learning.learningresult;

import com.edutie.backend.application.common.UseCaseHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultsSolutionSubmissionQuery;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import validation.WrapperResult;

public interface GetSolutionSubmissionByIdQueryHandler extends UseCaseHandler<WrapperResult<SolutionSubmission>, GetLearningResultsSolutionSubmissionQuery> {
}

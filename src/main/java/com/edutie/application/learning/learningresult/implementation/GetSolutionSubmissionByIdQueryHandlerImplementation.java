package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.learning.learningresult.GetSolutionSubmissionByIdQueryHandler;
import com.edutie.application.learning.learningresult.queries.GetLearningResultsSolutionSubmissionQuery;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetSolutionSubmissionByIdQueryHandlerImplementation implements GetSolutionSubmissionByIdQueryHandler {
    private final LearningResultPersistence learningResultPersistence;

    @Override
    public WrapperResult<SolutionSubmissionBase> handle(GetLearningResultsSolutionSubmissionQuery query) {
        log.info("Retrieving solution submission of learning result of id {} by student user of id {}", query.learningResultId(), query.studentUserId());
        return learningResultPersistence.getById(query.learningResultId()).map(LearningResult::getSolutionSubmission);
    }
}

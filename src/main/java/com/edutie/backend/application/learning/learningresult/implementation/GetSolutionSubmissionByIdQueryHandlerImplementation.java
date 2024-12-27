package com.edutie.backend.application.learning.learningresult.implementation;

import com.edutie.backend.application.learning.learningresult.GetSolutionSubmissionByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetSolutionSubmissionByIdQuery;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetSolutionSubmissionByIdQueryHandlerImplementation implements GetSolutionSubmissionByIdQueryHandler {
    private final SolutionSubmissionPersistence solutionSubmissionPersistence;
    @Override
    public WrapperResult<SolutionSubmission> handle(GetSolutionSubmissionByIdQuery query) {
        log.info("Retrieving solution submission of id {} by student user of id {}", query.solutionSubmissionId(), query.studentUserId());
        return solutionSubmissionPersistence.getById(query.solutionSubmissionId());
    }
}

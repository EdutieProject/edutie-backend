package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetLearningResultByIdQueryHandlerImplementation implements GetLearningResultByIdQueryHandler {
    private final LearningResultPersistence learningResultPersistence;
    @Override
    public WrapperResult<LearningResult> handle(GetLearningResultByIdQuery query) {
        log.info("Retrieving learning result of id {} by student user of id {}", query.learningResultId(), query.studentUserId());
        return learningResultPersistence.getById(query.learningResultId());
    }
}

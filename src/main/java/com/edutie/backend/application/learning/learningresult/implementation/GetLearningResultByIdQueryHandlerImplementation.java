package com.edutie.backend.application.learning.learningresult.implementation;

import com.edutie.backend.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
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

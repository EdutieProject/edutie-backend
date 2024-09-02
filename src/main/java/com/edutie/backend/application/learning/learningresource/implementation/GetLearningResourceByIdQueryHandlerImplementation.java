package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class GetLearningResourceByIdQueryHandlerImplementation extends HandlerBase implements GetLearningResourceByIdQueryHandler {
    private final LearningResourcePersistence learningResourcePersistence;
    @Override
    public WrapperResult<LearningResource> handle(GetLearningResourceByIdQuery query) {
        LOGGER.info("Retrieving learning resource of id {} by student user of id {}", query.learningResourceId(), query.studentUserId());
        return learningResourcePersistence.getById(query.learningResourceId());
    }
}

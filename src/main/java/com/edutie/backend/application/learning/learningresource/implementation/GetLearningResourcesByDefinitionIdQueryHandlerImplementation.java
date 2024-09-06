package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.GetLearningResourcesByDefinitionIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetLearningResourcesByDefinitionIdQueryHandlerImplementation extends HandlerBase implements GetLearningResourcesByDefinitionIdQueryHandler {
    private final LearningResourcePersistence learningResourcePersistence;

    @Override
    public WrapperResult<List<LearningResource>> handle(GetLearningResourcesByDefinitionIdQuery query) {
        LOGGER.info("Retrieving learning resources by definition id {} for student user of id {}",
                query.learningResourceDefinitionId(), query.studentUserId());
        return learningResourcePersistence.getByLearningResourceDefinitionId(query.learningResourceDefinitionId());
    }
}

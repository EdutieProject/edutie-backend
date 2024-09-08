package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.GetLearningResourcesByDefinitionIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLearningResourcesByDefinitionIdQueryHandlerImplementation extends HandlerBase implements GetLearningResourcesByDefinitionIdQueryHandler {
	private final LearningResourcePersistence learningResourcePersistence;

	@Override
	public WrapperResult<List<LearningResource>> handle(GetLearningResourcesByDefinitionIdQuery query) {
		log.info("Retrieving learning resources by definition id {} for student user of id {}", query.learningResourceDefinitionId(), query.studentUserId());
		return learningResourcePersistence.getByLearningResourceDefinitionId(query.learningResourceDefinitionId());
	}
}

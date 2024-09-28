package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLearningResourceByIdQueryHandlerImplementation extends HandlerBase implements GetLearningResourceByIdQueryHandler {
	private final LearningResourcePersistence learningResourcePersistence;

	@Override
	public WrapperResult<LearningResource> handle(GetLearningResourceByIdQuery query) {
		log.info("Retrieving learning resource of id {} by student user of id {}", query.learningResourceId(), query.studentUserId());
		return learningResourcePersistence.getById(query.learningResourceId());
	}
}

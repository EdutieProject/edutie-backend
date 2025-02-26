package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningResourcePersistence;
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
	public WrapperResult<LearningExperience> handle(GetLearningResourceByIdQuery query) {
		log.info("Retrieving learning resource of id {} by student user of id {}", query.learningResourceId(), query.studentUserId());
		return learningResourcePersistence.getById(query.learningResourceId());
	}
}

package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.learning.learningresource.GetLearningResourcesByDefinitionIdQueryHandler;
import com.edutie.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLearningResourcesByDefinitionIdQueryHandlerImplementation extends HandlerBase implements GetLearningResourcesByDefinitionIdQueryHandler {
	private final LearningExperiencePersistence learningExperiencePersistence;

	@Override
	public WrapperResult<List<LearningExperience>> handle(GetLearningResourcesByDefinitionIdQuery query) {
		log.info("Retrieving learning resources by definition id {} for student user of id {}", query.learningResourceDefinitionId(), query.studentUserId());
		return learningExperiencePersistence.getByLearningResourceDefinitionId(query.learningResourceDefinitionId());
	}
}

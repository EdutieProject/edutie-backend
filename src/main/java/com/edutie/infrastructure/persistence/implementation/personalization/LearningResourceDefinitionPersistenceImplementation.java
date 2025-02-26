package com.edutie.infrastructure.persistence.implementation.personalization;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.jpa.repositories.EducatorRepository;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LearningResourceDefinitionPersistenceImplementation implements LearningResourceDefinitionPersistence {
	private final EducatorRepository educatorRepository;
	private final LearningResourceDefinitionRepository learningResourceDefinitionRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<StaticLearningResourceDefinition, LearningResourceDefinitionId> getRepository() {
		return learningResourceDefinitionRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<StaticLearningResourceDefinition> entityClass() {
		return StaticLearningResourceDefinition.class;
	}

	@Override
	public WrapperResult<List<StaticLearningResourceDefinition>> getByAuthorEducator(EducatorId educatorId) {
		Optional<List<StaticLearningResourceDefinition>> learningResourceDefinitionList = educatorRepository.findById(educatorId).map(learningResourceDefinitionRepository::getByAuthorEducator);
		return learningResourceDefinitionList.map(WrapperResult::successWrapper).orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(Educator.class)));
	}
}

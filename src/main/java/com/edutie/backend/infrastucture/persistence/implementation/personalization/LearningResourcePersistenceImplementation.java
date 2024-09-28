package com.edutie.backend.infrastucture.persistence.implementation.personalization;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.LearningResourceDefinitionRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.LearningResourceRepository;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LearningResourcePersistenceImplementation implements LearningResourcePersistence {
	private final LearningResourceRepository learningResourceRepository;
	private final LearningResourceDefinitionRepository learningResourceDefinitionRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<LearningResource, LearningResourceId> getRepository() {
		return learningResourceRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningResource> entityClass() {
		return LearningResource.class;
	}

	/**
	 * Retrieve all Learning Resources associated with given definition
	 *
	 * @param learningResourceDefinitionId definition id
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<LearningResource>> getByLearningResourceDefinitionId(LearningResourceDefinitionId learningResourceDefinitionId) {
		try {
			Optional<LearningResourceDefinition> definitionOptionalWrapper = learningResourceDefinitionRepository.findById(learningResourceDefinitionId);
			return definitionOptionalWrapper.map(definition -> WrapperResult.successWrapper(learningResourceRepository.getAllByDefinition(definition))).orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(LearningResourceDefinition.class)));
		} catch (Exception ex) {
			return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
		}
	}
}

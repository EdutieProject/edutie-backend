package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.infrastructure.persistence.PersistenceError;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningResourceDefinitionRepository;
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
	public JpaRepository<LearningResourceDefinition, LearningResourceDefinitionId> getRepository() {
		return learningResourceDefinitionRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningResourceDefinition> entityClass() {
		return LearningResourceDefinition.class;
	}

	@Override
	public WrapperResult<List<LearningResourceDefinition>> getByAuthorEducator(EducatorId educatorId) {
		Optional<List<LearningResourceDefinition>> learningResourceDefinitionList = educatorRepository.findById(educatorId).map(learningResourceDefinitionRepository::getByAuthorEducator);
		return learningResourceDefinitionList.map(WrapperResult::successWrapper).orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(Educator.class)));
	}
}

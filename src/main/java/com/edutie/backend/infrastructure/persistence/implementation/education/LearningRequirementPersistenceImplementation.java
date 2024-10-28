package com.edutie.backend.infrastructure.persistence.implementation.education;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningRequirementRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class LearningRequirementPersistenceImplementation implements LearningRequirementPersistence {
	private final LearningRequirementRepository learningRequirementRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<LearningRequirement, LearningRequirementId> getRepository() {
		return learningRequirementRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningRequirement> entityClass() {
		return LearningRequirement.class;
	}

}

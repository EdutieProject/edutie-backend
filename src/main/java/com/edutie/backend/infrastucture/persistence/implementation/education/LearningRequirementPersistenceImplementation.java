package com.edutie.backend.infrastucture.persistence.implementation.education;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.LearningRequirementRepository;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;

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

	/**
	 * Persists the provided learningRequirement into the database. If it is already present,
	 * updates its state to the provided object's state. Returns result indicating whether
	 * the operation was successful or not
	 *
	 * @param learningRequirement learning requirement
	 * @return Result object
	 */
	@Override
	public Result save(LearningRequirement learningRequirement) {
		try {
			getRepository().save(learningRequirement);
			return Result.success();
		} catch (Exception exception) {
			return Result.failure(PersistenceError.exceptionEncountered(exception));
		}
	}

	/**
	 * Retrieve all learning requirements associated with given creator
	 *
	 * @param educatorId educator id
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<LearningRequirement>> getAllOfEducatorId(EducatorId educatorId) {
		return null;
	}

	/**
	 * Retrieve all learning requirements associated with given science
	 *
	 * @param scienceId science id
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<LearningRequirement>> getAllOfScienceId(ScienceId scienceId) {
		return null;
	}
}

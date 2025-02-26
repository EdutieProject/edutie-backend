package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.domain.core.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.domain.core.education.learningrequirement.persistence.LearningSubjectPersistence;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.jpa.repositories.LearningRequirementRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LearningSubjectPersistenceImplementation implements LearningSubjectPersistence {
	private final LearningRequirementRepository learningRequirementRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<LearningSubject, LearningRequirementId> getRepository() {
		return learningRequirementRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningSubject> entityClass() {
		return LearningSubject.class;
	}

	/**
	 * Retrieves a number of learning requirements. If the returned list does not contain the specified number
	 * of requirements, that means there is not enough of them in the persistence.
	 *
	 * @param number number of l.reqs to retrieve
	 * @return
	 */
	@Override
	public WrapperResult<List<LearningSubject>> getAny(int number) {
		try {
			return WrapperResult.successWrapper(learningRequirementRepository.findRandom(number));
		} catch (Exception e) {
			return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(e));
		}

	}
}

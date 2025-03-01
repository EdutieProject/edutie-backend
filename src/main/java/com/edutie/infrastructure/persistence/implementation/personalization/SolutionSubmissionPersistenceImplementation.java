package com.edutie.infrastructure.persistence.implementation.personalization;

import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import com.edutie.domain.core.learning.learningresult.identities.SolutionSubmissionId;
import com.edutie.infrastructure.persistence.jpa.repositories.SolutionSubmissionRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class SolutionSubmissionPersistenceImplementation implements Persistence<SolutionSubmissionBase, SolutionSubmissionId> {
	private final SolutionSubmissionRepository solutionSubmissionRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<SolutionSubmissionBase, SolutionSubmissionId> getRepository() {
		return solutionSubmissionRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<SolutionSubmissionBase> entityClass() {
		return SolutionSubmissionBase.class;
	}
}

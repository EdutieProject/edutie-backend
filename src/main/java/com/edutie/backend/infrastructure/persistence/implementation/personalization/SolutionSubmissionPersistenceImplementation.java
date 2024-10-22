package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.SolutionSubmissionRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class SolutionSubmissionPersistenceImplementation implements SolutionSubmissionPersistence {
	private final SolutionSubmissionRepository solutionSubmissionRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<SolutionSubmission, SolutionSubmissionId> getRepository() {
		return solutionSubmissionRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<SolutionSubmission> entityClass() {
		return SolutionSubmission.class;
	}
}

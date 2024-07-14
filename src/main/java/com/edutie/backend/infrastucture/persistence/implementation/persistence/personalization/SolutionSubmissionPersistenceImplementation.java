package com.edutie.backend.infrastucture.persistence.implementation.persistence.personalization;

import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.SolutionSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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

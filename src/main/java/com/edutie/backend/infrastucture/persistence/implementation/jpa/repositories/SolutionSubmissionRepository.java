package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionSubmissionRepository extends JpaRepository<SolutionSubmission, SolutionSubmissionId> {
}

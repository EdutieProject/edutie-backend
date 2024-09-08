package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.identities.SolutionSubmissionId;
import org.springframework.data.jpa.repository.*;

public interface SolutionSubmissionRepository extends JpaRepository<SolutionSubmission, SolutionSubmissionId> { }

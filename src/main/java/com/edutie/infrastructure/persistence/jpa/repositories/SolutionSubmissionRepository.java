package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.learning.solutionsubmission.SolutionSubmission;
import com.edutie.domain.core.learning.solutionsubmission.identities.SolutionSubmissionId;
import org.springframework.data.jpa.repository.*;

public interface SolutionSubmissionRepository extends JpaRepository<SolutionSubmission, SolutionSubmissionId> { }

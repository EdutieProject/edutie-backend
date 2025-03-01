package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import com.edutie.domain.core.learning.learningresult.identities.SolutionSubmissionId;
import org.springframework.data.jpa.repository.*;

public interface SolutionSubmissionRepository extends JpaRepository<SolutionSubmissionBase, SolutionSubmissionId> { }

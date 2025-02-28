package com.edutie.domain.core.learning.learningresult.entities.submission.common;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningresult.identities.SolutionSubmissionId;
import jakarta.persistence.MappedSuperclass;

/**
 * A submitted solution for the learning resource.
 */
@MappedSuperclass
public abstract class SolutionSubmissionBase extends EntityBase<SolutionSubmissionId> implements SolutionSubmission {
}

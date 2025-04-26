package com.edutie.domain.core.learning.learningresult.entities.submission.base;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.learningresult.identities.SolutionSubmissionId;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

/**
 * A submitted solution for the learning resource.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class SolutionSubmissionBase extends EntityBase<SolutionSubmissionId> implements SolutionSubmission {
    public SolutionSubmissionBase() {
        this.setId(new SolutionSubmissionId());
    }
}

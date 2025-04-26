package com.edutie.domain.core.learning.learningresult.entities.submission;

import com.edutie.domain.core.learning.learningresult.entities.submission.base.AnalyzingActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

/**
 * A submitted solution for the learning resource.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class OnlineDiscussionSimulationActivitySolutionSubmission
        extends SolutionSubmissionBase implements AnalyzingActivitySolutionSubmission {
    //TODO
}

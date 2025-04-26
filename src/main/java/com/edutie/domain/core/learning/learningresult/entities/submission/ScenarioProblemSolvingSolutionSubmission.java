package com.edutie.domain.core.learning.learningresult.entities.submission;

import com.edutie.domain.core.learning.learningresult.entities.submission.base.ApplyingActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.ActivitySolutionParagraph;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * A submitted solution for the learning resource.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class ScenarioProblemSolvingSolutionSubmission
        extends SolutionSubmissionBase implements ApplyingActivitySolutionSubmission {
    @OneToMany(targetEntity = ActivitySolutionParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ActivitySolutionParagraph> solutionParagraphs = new ArrayList<>();
}

package com.edutie.domain.core.learning.learningresult.entities.submission;

import com.edutie.domain.core.learning.learningresult.entities.submission.base.RememberingActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmissionBase;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.ActivitySolutionParagraph;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A submitted solution for the learning resource.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Getter
public class SimpleProblemActivitySolutionSubmission
        extends SolutionSubmissionBase implements RememberingActivitySolutionSubmission {
    @OneToMany(targetEntity = ActivitySolutionParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonProperty
    private List<ActivitySolutionParagraph> solutionParagraphs = new ArrayList<>();

    public SimpleProblemActivitySolutionSubmission() {
        super();
    }

}

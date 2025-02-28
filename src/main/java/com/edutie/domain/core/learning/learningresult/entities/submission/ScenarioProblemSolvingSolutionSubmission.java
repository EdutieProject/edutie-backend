package com.edutie.domain.core.learning.learningresult.entities.submission;

import com.edutie.domain.core.learning.learningresult.entities.submission.common.ActivitySolutionParagraph;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.SolutionSubmissionBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * A submitted solution for the learning resource.
 */
@Entity
public class ScenarioProblemSolvingSolutionSubmission extends SolutionSubmissionBase {
    @OneToMany(targetEntity = ActivitySolutionParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ActivitySolutionParagraph> solutionParagraphs = new ArrayList<>();
}
